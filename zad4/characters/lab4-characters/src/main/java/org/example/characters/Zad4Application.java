package org.example.characters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Zad4Application {
	public static void main(String[] args) {
		SpringApplication.run(Zad4Application.class, args);
	}
	@Bean
	@Qualifier("library")
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder()
				.rootUri("http://localhost:8081/")
				.build();
	}
}
