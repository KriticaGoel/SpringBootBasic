package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertionsTesting {
	
	
	public void assertwithIfElse() {
		Calculations calculatorTest = new Calculations();
		if(calculatorTest.add(4,5) ==10)
			{
			System.out.println("Test Passed");
		}
		else
		{
			System.out.println("Test Failed");
		}
	}
	
	//@Test
	public void asserts() {
		Calculations calculatorTest = new Calculations();
		Assertions.assertEquals(9, calculatorTest.add(4,5),"Addition of(4,5) is failed");		
		System.out.println("Addition Test Passed");
		
	}
	
	//@Test
	public void assertsAlternative() {
		Calculations calculatorTest = new Calculations();
		assert(calculatorTest.add(4,5) ==9) : "Addition Test Failed";
		System.out.println("Addition Test Passed");
		
	}
	
	//@Test
		public void assertsNotEqual() {
			Calculations calculatorTest = new Calculations();
			Assertions.assertNotEquals(9, calculatorTest.add(4,5),"Addition of(4,5) is failed");		
			System.out.println("Addition Test Passed");
			
		}
		
	//@Test
	public void assertsWithArraysDemo() {
		int[] expectedArray = {1,2,3,4,5};
		int[] actualArray = {1,2,3,4,5};
		//Assertions.assertEquals(expectedArray, actualArray,"Arrays are not equal");//compare adddress
		Assertions.assertArrayEquals(expectedArray, actualArray,"Arrays are not equal");
	}
	
	//@Test
	public void assertsWithArraysFailed() {
		int[] expectedArray = {1,2,3,4,5};
		int[] actualArray = {2,1,3,4,5};
		//Assertions.assertEquals(expectedArray, actualArray,"Arrays are not equal");//compare adddress
		Assertions.assertArrayEquals(expectedArray, actualArray,"Arrays are not equal");
	}
	
	//@Test
	public void assertsTrueDemo() {
		String str1 = "Hello";
		Assertions.assertTrue(str1.startsWith("H"),"Condition is false");
		System.out.println("Condition is true");
	}
	
	
	@Test
	public void assertsGroup() {
		Calculations calculatorTest = new Calculations();
		Assertions.assertAll("Addition Tests",
				() -> Assertions.assertEquals(9, calculatorTest.add(4,5),"Addition of(4,5) is failed"),
				() -> Assertions.assertEquals(1, calculatorTest.add(-4,4),"Addition of(-4,4) is failed"),
				() -> Assertions.assertEquals(8, calculatorTest.add(-4,-4),"Addition of(-4,-4) is failed")
				);
		
	}
	
	
}
