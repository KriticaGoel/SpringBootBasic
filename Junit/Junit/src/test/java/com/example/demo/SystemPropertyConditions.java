package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

public class SystemPropertyConditions {
	
	@Test
	public void getSystemProperties() {
		System.out.println("System Properties: " + System.getProperties());
		System.out.println("Java Version: " + System.getProperty("java.version"));
	}
	
	@Test
	@EnabledIfSystemProperty(named = "os.name", matches = ".*Windows.*")
	public void runOnlyOnWindows() {
		System.out.println("This test runs only on Windows OS");
	}

}
