package com.gemography.github.list.languages.api.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;

@Configuration
public class Config {

    @Value("${date.format}")
    String format;

    @Bean
    public DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ofPattern(format);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
