package com.microservice.hrapigatewayzuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private JwtTokenStore tokenStore;

    private static final String[] PUBLIC = {"/hr-oauth/oauth/token", };
    private static final String[] OPERATOR = {"/hr-worker/**", };
    private static final String[] ADMIN = {"/hr-payroll/**", "/hr-user/**"};

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception { //configurar as autorizaçoes
        http.authorizeRequests()
            .antMatchers(PUBLIC).permitAll()//define as autoziações
            .antMatchers(HttpMethod.GET, OPERATOR).hasAnyRole("OPERATOR", "ADMIN") //atoriza para mais de um perfil, o metodo get
            .antMatchers(ADMIN).hasRole("ADMIN")//autoriza todas as rodas da string para perfil de ADMIN
            .anyRequest().authenticated();//qualquer outra request pode acessar porem precisa ser autenticada
    }
}
