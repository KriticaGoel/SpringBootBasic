package com.example.demo;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

public class RepeatedTests {
	@Tag("feature1")
	@RepeatedTest(5)
	public void repeatTest() {
		System.out.println("This test is repeated 5 times");
	}

}
