/**
 * 
 */
package com.crossover.techtrial;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author crossover
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CrossRideApplicationTest {
	
	MockMvc mockMvc;
	@Mock
	CrossRideApplication app;
	
	@Before
	  public void setup() throws Exception {
	    mockMvc = MockMvcBuilders.standaloneSetup(app).build();
	  }
	  
	
	@Test
	  public void testRegisterRide() throws Exception {
		CrossRideApplication.main(new String[]{});
	}
}
