package com.example.demo;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.StringUtils;

public class ParameterizedTestDemo {
	
	//@ParameterizedTest
	@ValueSource(strings = {"Apple", "Ball", "Avocado"})
	@EmptySource
	@NullSource
	@NullAndEmptySource
	public void parameterizedTestExample(String variable) {
		Assertions.assertTrue(variable.isEmpty(), "Variable starts with A");
		System.out.println("Parameterized test executed with value: " + variable);
	}
	
	//@ParameterizedTest
	@EnumSource(Values.class)
	public void parameterizedTestWithEnum(Values day) {
		Assertions.assertTrue(StringUtils.hasText(day.toString()), "Enum value should not be null");
		System.out.println("Parameterized test executed with enum value: " + day);
	}
	
	
	//@ParameterizedTest
	@EnumSource(value=Values.class, mode = EnumSource.Mode.EXCLUDE, names={"TWO", "FOUR"})
	public void parameterizedTestWithEnum1(Values day) {
		Assertions.assertTrue(StringUtils.hasText(day.toString()), "Enum value should not be null");
		System.out.println("Parameterized test executed with enum value: " + day);
	}
	
	
	enum Values{
		ONE, TWO, THREE, FOUR, FIVE
	}
	
	//@ParameterizedTest
	@MethodSource("stringProvider")
	public void useMethodSourceString(String parameter) {

		Assertions.assertTrue(StringUtils.hasText(parameter), "Parameter should not be null or empty");
		System.out.println("Parameterized test executed with method source value: " + parameter);
	}

static Stream<String> stringProvider() {
		return Stream.of("apple", "banana");
	}

//@ParameterizedTest
@CsvSource( {"apple,banana", "carrot,durian"})
public void testWithCsvSource(String first, String second) {
	Assertions.assertTrue(StringUtils.hasText(first), "First parameter should not be null or empty");
	Assertions.assertTrue(StringUtils.hasText(second), "Second parameter should not be null or empty");
	System.out.println("Parameterized test executed with CSV source values: " + first + ", " + second);
}

@ParameterizedTest
@Tag("feature1")
@CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
public void testWithCsvSource1(String first, String second) {
	Assertions.assertTrue(StringUtils.hasText(first), "First parameter should not be null or empty");
	Assertions.assertTrue(StringUtils.hasText(second), "Second parameter should not be null or empty");
	System.out.println("Parameterized test executed with CSV source values: " + first + ", " + second);
}

}
