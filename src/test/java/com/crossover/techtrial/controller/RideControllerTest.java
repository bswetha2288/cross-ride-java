package com.crossover.techtrial.controller;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.repositories.RideRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RideControllerTest {

	 MockMvc mockMvc;
	 Pageable pageable;
	  
	 @Autowired
	  private RideController rideController;
	  
	  @Autowired
	  private TestRestTemplate template;
	  
	  @Autowired
	  RideRepository rideRepository;
	  
	  @Before
	  public void setup() throws Exception {
	    mockMvc = MockMvcBuilders.standaloneSetup(rideController).build();
	  }
	  
	  @Test
	  public void testRegisterRide() throws Exception {
	    HttpEntity<Object> ride = getHttpEntity(
	    	"{\"startTime\" : \"2018-10-20T13:06:25\","
	    	+"\"endTime\" : \"2018-10-20T13:16:25\","
	    	+"\"distance\" : 10,"
	    	+"\"driver\" : {\"id\" : 1},"
	    	+"\"rider\" : {\"id\" : 8}}");
	    
	    ResponseEntity<Ride> response = template.postForEntity(
	        "/api/ride", ride, Ride.class);
	    
	    LocalDateTime aStartTime = LocalDateTime.of(2018, Month.OCTOBER, 20, 13, 16, 25);
	    
	    Assert.assertEquals(aStartTime, response.getBody().getEndTime());
	    Assert.assertEquals(200,response.getStatusCode().value());
	   }
	  
	  @Test
	  public void testRegisterRideNull() throws Exception {
	    HttpEntity<Object> ride = getHttpEntity(
	    	"{\"startTime\" : \"2018-10-20T13:06:25\","
	    	//+"\"endTime\" : \"2018-10-20T13:16:25\","
	    	+"\"distance\" : 10,"
	    	+"\"driver\" : {\"id\" : 1},"
	    	+"\"rider\" : {\"id\" : 8}}");
	    
	    ResponseEntity<Ride> response = template.postForEntity(
	        "/api/ride", ride, Ride.class);
	    
	    Assert.assertNull(response.getBody().getEndTime());
	    Assert.assertEquals(400,response.getStatusCode().value());
	   }
	  
	  @Test
	  public void testRegisterRideNotNull() throws Exception {
	    HttpEntity<Object> ride = getHttpEntity(
	    	"{\"startTime\" : \"2018-10-20T13:06:25\","
	    	+"\"endTime\" : \"2018-10-20T13:16:25\","
	    	+"\"distance\" : 10,"
	    	+"\"driver\" : {\"id\" : 1},"
	    	+"\"rider\" : {\"id\" : 8}}");
	    
	    ResponseEntity<Ride> response = template.postForEntity(
	        "/api/ride", ride, Ride.class);
	    
	    Assert.assertNotNull(response.getBody().getEndTime());
	    Assert.assertEquals(200,response.getStatusCode().value());
	   }
	  
	  @Test
	  public void testFindRideById() throws Exception {
		  //find ride by id
		  ResponseEntity<Ride> ride = rideController.getRideById(1L);
		  Assert.assertEquals(200,ride.getStatusCode().value());
	      
	  }
	  @Test
	  public void testFindRideById404Error() throws Exception {
		  //find ride by id
		  ResponseEntity<Ride> ride = rideController.getRideById(5L);
		  Assert.assertEquals(404,ride.getStatusCode().value());
	      
	  }
	  
	  @Test
	  public void testFindRideFetchString() throws Exception {
		  //find ride by id
		  ResponseEntity<Ride> ride = rideController.getRideById(1L);
		  Assert.assertTrue(ride.toString().contains("distance"));
	      
	  }
	 
	  @Test
	  public void testtopDriversList() {
		  //find All Rides
	      
		  LocalDateTime aEndTime = LocalDateTime.of(2018, Month.OCTOBER, 20, 13, 06, 25);
		  
	 	  LocalDateTime aStartTime = LocalDateTime.of(2018, Month.OCTOBER, 20, 14, 16, 25);
		  
		  ResponseEntity<List<TopDriverDTO>> returned = rideController.getTopDriver(5L, aStartTime, aEndTime);
	      
	      Assert.assertEquals(200, returned.getStatusCodeValue());
	  }
	 	 
	  @Test
	  public void testFindRideByHashcode() throws Exception {
		  //find ride by id
		  ResponseEntity<Ride> ride1 = rideController.getRideById(1L);
		  ResponseEntity<Ride> ride2 = rideController.getRideById(1L);
		  Assert.assertTrue(ride1.equals(ride2));
		  Assert.assertTrue(ride1.hashCode()==ride2.hashCode());
		  
	      
	  }  
	   private HttpEntity<Object> getHttpEntity(Object body) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    return new HttpEntity<Object>(body, headers);


}
}