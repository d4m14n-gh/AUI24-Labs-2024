package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(
            RouteLocatorBuilder builder,
            @Value("${character.url}") String characterUrl,
            @Value("${profession.url}") String professionUrl,
            @Value("${gateway.host}") String host
    ) {
        System.out.println(characterUrl);
        System.out.println(professionUrl);
        System.out.println(host);

        return builder
                .routes()
                .route("professions", route -> route
                        //.host(host)
                        //.and()
                        .path(
                                "/api/professions/{uuid}",
                                "/api/professions"
                        )
                        .uri(professionUrl)
                )
                .route("characters", route -> route
                        //.host(host)
                        //.and()
                        .path(
                                "/api/characters/{uuid}",
                                "/api/characters",
                                "/api/professions/{uuid}/characters"
                        )
                        .uri(characterUrl)
                )
                .build();
    }
    @Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

}