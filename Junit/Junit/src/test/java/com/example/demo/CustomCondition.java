package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;

public class CustomCondition {
	
	@Test
	@EnabledIf("isConditionMet")
	public void customCondition() {
		System.out.println("This is a custom condition example");
	}

	public boolean isConditionMet() {
		// Custom logic to determine if the condition is met
		int a = 10;
		int b = 20;
		if (a + b > b) {
			return true;
		} else {
			return true;
		}
	}

}
