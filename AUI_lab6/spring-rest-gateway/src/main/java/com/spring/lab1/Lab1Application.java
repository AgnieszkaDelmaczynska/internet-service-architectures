package com.spring.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication(scanBasePackages = {"com.spring.lab1"})
public class Lab1Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab1Application.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("genres", r -> r
                        .host("localhost")
                        .and()
                        .path("/api/genres/{name}", "/api/genres")
                        .uri("http://gen:8082")) //http://localhost:8082
                .route("cinemas", r -> r
                        .host("localhost")
                        .and()
                        .path("/api/cinemas", "/api/cinemas/**", "/api/genres/{name}/cinemas", "/api/genres/{name}/cinemas/**")
                        .uri("http://cin:8081")) //http://localhost:8081
                .route("files", r -> r
                        .host("localhost")
                        .and()
                        .path("/api/files", "/api/files/**", "/api/files/{title}/details")
                        .uri("http://file:8084"))
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
