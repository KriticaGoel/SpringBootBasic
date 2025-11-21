package com.example.demo;

public class Calculations {

	public int add(int a, int b) {
		return a + b;
	}
	
	public double divide(int a, int b) {
		if (b == 0) {
			throw new IllegalArgumentException("Division by zero is not allowed.");
		}
		return (double) a / b;
	}
}
