package com.beautyservices.bliss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@SpringBootApplication
public class BlissApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlissApplication.class, args);
	}

	
	@Configuration
	public static class Myconfiguration{
		@Bean
		public WebMvcConfigurer corsConfigurer() {
		    return new WebMvcConfigurer() {
		        @Override
		        public void addCorsMappings(CorsRegistry registry) {
		            registry.addMapping("/**")
		                    .allowedOrigins("https://exquisite-possibility-production.up.railway.app") // Cambia por tu dominio
		                    .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
		                    .allowedHeaders("Authorization", "Content-Type", "Accept")
		                    .allowCredentials(true);
		        }
		    };
		}


	

}
