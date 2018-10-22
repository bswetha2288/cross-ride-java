package com.crossover.techtrial;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.crossover.techtrial.controller.PersonControllerTest;
import com.crossover.techtrial.controller.RideControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ CrossRideApplicationTest.class,PersonControllerTest.class,RideControllerTest.class})
public class AllTests {

}
