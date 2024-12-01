package org.example.profession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Zad4Application {
	public static void main(String[] args) {
		SpringApplication.run(Zad4Application.class, args);
	}
	@Bean
	@Qualifier("library")
	public RestTemplate restTemplate(@Value("${character.url}") String baseUrl) {
		return new RestTemplateBuilder()
				.rootUri(baseUrl+"/")
				.build();
	}
}
