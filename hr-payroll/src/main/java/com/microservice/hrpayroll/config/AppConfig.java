package com.microservice.hrpayroll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean //medotod que via ficar disponivel para criar a intancia para ser usado em outro ms (single instancia unica// )
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
