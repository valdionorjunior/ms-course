package com.microservice.hroauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope //atualiza os valores das variavei em tempo de execucao com a dependencia actuator
@Configuration
public class AppConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;//chave sendo puxada do git

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){ //configurando access token com a assinatura da api
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(jwtSecret); //chave
        return tokenConverter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore(){//reponsavel por ler o token
        return new JwtTokenStore(accessTokenConverter());
    }
}
