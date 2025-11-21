package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

public class AssumptionDemo {
	
	@Test
	public void assumptionTest() {
		Assumptions.assumeTrue("Kritica".startsWith("k"));
		System.out.println("This test runs only if the assumption is true");
	}
	
	@Test
	public void assumptionTestFalse() {
		Assumptions.assumeFalse("Kritica".startsWith("k"));
		System.out.println("This test runs only if the assumption is false");
	}
	
	@Test
	public void assertionTestFalse() {
		Assertions.assertFalse("Kritica".startsWith("K"));
		System.out.println("This test runs only if the assumption is false");
	}

}
