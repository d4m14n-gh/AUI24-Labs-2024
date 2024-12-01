package org.example.zad3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class Zad3Application {
	public static void main(String[] args) {
		SpringApplication.run(Zad3Application.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Wzorzec URL, np. wszystkie endpointy
						.allowedOrigins("http://localhost:4200") // Dozwolone domeny
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Dozwolone metody
						.allowedHeaders("*") // Dozwolone nagłówki
						.allowCredentials(true); // Czy zezwolić na ciasteczka
			}
		};
	}

}
