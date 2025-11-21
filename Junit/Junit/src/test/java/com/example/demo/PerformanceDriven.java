package com.example.demo;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


@Timeout(5)
public class PerformanceDriven {

	
	@Test
	public void performanceTest() throws InterruptedException {
		Thread.sleep(4000); // Simulating a long-running task
	}
	
	@Test
	public void performanceTest1() throws InterruptedException {
		Thread.sleep(8000); // Simulating a long-running task
	}
	
	
	@Test
	//@Timeout(8)
	@Timeout(value = 8000,unit=TimeUnit.MILLISECONDS)
	public void performanceTest2() throws InterruptedException {
		Thread.sleep(7000); // Simulating a long-running task
	}
	
	@Test
	public void performanceTest3() throws InterruptedException {
		Assertions.assertTimeout(Duration.ofMillis(7000), () -> {
			Thread.sleep(3000); // Simulating a long-running task
		});
	}
	
	@Test
	public void performanceTest4() throws InterruptedException {
		Assertions.assertTimeoutPreemptively(Duration.ofMillis(7000), () -> {
			Thread.sleep(3000); // Simulating a long-running task
		});
	}
	
	@Test
	public void validateExpectedException() {
		Assertions.assertThrows(ArithmeticException.class, () -> {
			int result = 10 / 0; // This will throw ArithmeticException
		});
	}
	
	
}
