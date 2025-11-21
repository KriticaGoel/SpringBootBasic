package com.example.demo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControlOrderExeccution {

	@Test
	@Order(1)
	public void test1() {
		System.out.println("Test 1 executed");
	} 
	
	@Test
	@Order(2)
	public void test2() {
		System.out.println("Test 2 executed");
	}
	@Test
	@Order(3)
	public void test3() {
		System.out.println("Test 3 executed");
	}
	@Test
	@Order(4)
	public void test4() {
		System.out.println("Test 4 executed");
	}
}
