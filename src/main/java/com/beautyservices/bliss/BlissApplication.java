package com.beautyservices.bliss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlissApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlissApplication.class, args);
	}

}
