package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(
            RouteLocatorBuilder builder
    ) {
        return builder
                .routes()
                .route("professions", route -> route
                        .host("localhost:8080")
                        .and()
                        .path(
                                "/api/professions/{uuid}",
                                "/api/professions"
                        )
                        .uri("http://localhost:8081")
                )
                .route("characters", route -> route
                        .host("localhost:8080")
                        .and()
                        .path(
                                "/api/characters/{uuid}",
                                "/api/characters",
                                "/api/professions/{uuid}/characters"
                        )
                        .uri("http://localhost:8082")
                )
                .build();
    }
}