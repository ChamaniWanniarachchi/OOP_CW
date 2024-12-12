package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
/**
 * The main method serves as the application's starting point.
 * It delegates to Spring Boot's SpringApplication.run method, which:
 * - Creates an application context.
 * - Initializes Spring Beans and the entire application lifecycle.
 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
