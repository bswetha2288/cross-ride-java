/**
 * 
 */
package com.crossover.techtrial.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.repositories.PersonRepository;

/**
 * @author kshah
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {
  
  MockMvc mockMvc;
  
  @Autowired
  private PersonController personController;
  
  @Autowired
  private TestRestTemplate template;
  
  @Autowired
  PersonRepository personRepository;
  
  @Before
  public void setup() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
  }
  
  @Test
  public void testRegisterPerson() throws Exception {
    HttpEntity<Object> person = getHttpEntity(
        "{\"name\": \"Lucky\", \"email\": \"Lucky.salad@gmail.com\"," 
            + " \"registrationNumber\": \"abc123\" }");
    ResponseEntity<Person> response = template.postForEntity(
        "/api/person", person, Person.class);
    
    Assert.assertEquals("Lucky", response.getBody().getName());
    Assert.assertEquals(200,response.getStatusCode().value());
   }
  
  @Test
  public void testRegisterPersonNotNull() throws Exception {
    HttpEntity<Object> person = getHttpEntity(
        "{\"name\": \"Lucky\", \"email\": \"Lucky.salad@gmail.com\"," 
            + " \"registrationNumber\": \"abc123\" }");
    ResponseEntity<Person> response = template.postForEntity(
        "/api/person", person, Person.class);
    
    Assert.assertNotNull("Lucky", response.getBody().getName());
    Assert.assertEquals(200,response.getStatusCode().value());
   }
  
  @Test
  public void testRegisterPersonNull() throws Exception {
    HttpEntity<Object> person = getHttpEntity(
        "{\"email\": \"Lucky.salad@gmail.com\"," 
            + " \"registrationNumber\": \"abc123\" }");
    ResponseEntity<Person> response = template.postForEntity(
        "/api/person", person, Person.class);
    
    Assert.assertNull(response.getBody().getName());
    Assert.assertEquals(200,response.getStatusCode().value());
   }
  
  @Test
  public void testFindPersonById() throws Exception {
	  Person p = new Person();
	  p.setEmail("Lucky.salad@gmail.com");
	  p.setId(1L);
	  p.setName("Lucky");
	  p.setRegistrationNumber("abc123");
	 
	  ResponseEntity<Person> person = personController.getPersonById(1L);
	  Assert.assertEquals(200,person.getStatusCodeValue());
      }
  
  @Test
  public void testFindPersonById404Error() throws Exception {
	  Person p = new Person();
	  p.setEmail("Lucky.salad@gmail.com");
	  p.setId(1L);
	  p.setName("Lucky");
	  p.setRegistrationNumber("abc123");
	 
	  ResponseEntity<Person> person = personController.getPersonById(200L);
	  
	  Assert.assertEquals(404,person.getStatusCodeValue());
      }
  
  
  @Test
  public void testPersonfindAll() {
	  //find person by id
      
      ResponseEntity<List<Person>> persons= personController.getAllPersons();
      Assert.assertEquals(200, persons.getStatusCode().value());
  }
  

  @Test
  public void testFindPersonFetchString() throws Exception {
	  //find ride by id
	  ResponseEntity<Person> person = personController.getPersonById(1L);
	  Assert.assertTrue(person.toString().contains("email"));
      
  }
  
  @Test
  public void testFindPersonHashcode() throws Exception {
	  //find ride by id
	  ResponseEntity<Person> person1 = personController.getPersonById(1L);
	  ResponseEntity<Person> person2 = personController.getPersonById(1L);
	  Assert.assertTrue(person1.equals(person2));
	  Assert.assertTrue(person1.hashCode()==person2.hashCode());
      
  }
 
  private HttpEntity<Object> getHttpEntity(Object body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<Object>(body, headers);
  }

}
