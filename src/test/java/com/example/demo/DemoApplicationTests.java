package com.example.demo;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static java.lang.Thread.sleep;

@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() throws InterruptedException {
		System.out.println("Test executed successfully");
		sleep(3000); // Simulate a delay of 2 seconds
	}

	@Test
	public void shouldFail() {
//		throw new RuntimeException("Intentional failure for testing purposes");
	}
}
