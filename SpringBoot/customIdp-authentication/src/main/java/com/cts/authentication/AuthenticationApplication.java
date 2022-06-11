package com.cts.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring boot app.
 *
 */
@SpringBootApplication
public class AuthenticationApplication {

    public static void main(String[] args) {

        SpringApplication.run(AuthenticationApplication.class, args);
    }
    
    @Bean
	public WebMvcConfigurer configure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry cors) {
				cors.addMapping("/*").allowedOrigins("*");
			}
		};
	}
}
