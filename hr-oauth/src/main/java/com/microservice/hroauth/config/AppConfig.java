package com.microservice.hroauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){ //configurando access token com a assinatura da api
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey("MY-SECRET_KEY"); //chave
        return tokenConverter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore(){//reponsavel por ler o token
        return new JwtTokenStore(accessTokenConverter());
    }
}
