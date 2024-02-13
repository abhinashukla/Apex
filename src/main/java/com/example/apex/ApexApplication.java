package com.example.apex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApexApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApexApplication.class, args);
		System.out.println("Apex project running...");
	}

}
