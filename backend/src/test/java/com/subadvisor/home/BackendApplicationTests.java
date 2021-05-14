package com.subadvisor.home;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BackendApplicationTests {

	@Test
	void contextLoads() {

	}

	@Test
	void testHomeController(){
		TestController testController = new TestController();
		String response = testController.greeting();
		assertEquals(response, "Hey there, this is the backend-service for sub-advisor!");
	}
}
