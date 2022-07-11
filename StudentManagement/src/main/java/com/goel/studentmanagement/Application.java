package com.goel.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//(scanBasePackages = { "com.goel.studentmanagement" })
	public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}