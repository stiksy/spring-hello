package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static java.lang.Thread.sleep;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() throws InterruptedException {
		System.out.println("Test executed successfully");
		sleep(3000); // Simulate a delay of 2 seconds
	}

}
