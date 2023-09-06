package com.pedroza.projetocrud.configs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsEnable {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();    
        config.addAllowedOrigin("http://127.0.0.1:5500");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        var bean = new CorsFilter(source);        
        return bean;
    }
}
