package com.example.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {
	
	@BeforeAll
	public static void openDatabase() {
		System.out.println("Database connection opened");
	}
	
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Initialization done");
	}
	
	@Test
	@DisplayName("Addition Test")
	void testAdd() {
		System.out.println("Addition Test executed");
		
	}
	
	@Test
	@DisplayName("Subtraction Test")
	void testSubtract() {
		System.out.println("Subtraction Test executed");
		
	}
	
	@AfterEach
	void cleanUp() throws Exception {
		System.out.println("Clear the resources");
	}
	
	@AfterAll
	public static void closeDatabase() {
		System.out.println("Database connection closed");
	}   
}
