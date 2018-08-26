package com.ubs.accumulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StringAccumulatorApplication {

	public static void main(String[] args) {

		SpringApplication.run(StringAccumulatorApplication.class, args);
		System.out.println("Result : "+StringAccumulator.add("1\n2,3"));
	}
}
