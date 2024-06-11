package com.cineverse.erpc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:8080",
                "http://www.erpc.site", "http://erpc.site",
                "https://www.erpc.site", "https://erpc.site",
                "http://erpc-frontend-env.eba-d36ceqsu.ap-northeast-2.elasticbeanstalk.com/",
                "http://localhost:8080"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setExposedHeaders(Arrays.asList("Authorization", "Set-Cookie", "Cookie"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}