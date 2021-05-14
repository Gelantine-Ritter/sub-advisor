package com.subadvisor.home;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BackendApplicationTests {

	@Test
<<<<<<< HEAD
	void testHomeController(){
		TestController testController = new TestController();
		String response = testController.greeting();
		assertEquals(response, "Hey there, this is the backend-service for sub-advisor!");
	}
=======
	void contextLoads() {

	}

	@Test
	void testHomeController(){
		TestController testController = new TestController();
		String response = testController.greeting();
		assertEquals(response, "Hey there, this is the backend-service for sub-advisor!");
	}
>>>>>>> 6f18cd4ed2e3d93f4f7581287840f36a672247d7
}
