package ar.edu.unnoba.pdyc.gateway_service;

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("song-service", r -> r.path("/songs/**")
                        .uri("lb://song-service"))
                .route("playlist-service", r -> r.path("/playlists/**")
                        .uri("lb://playlist-service"))
                .build();
    }

}
