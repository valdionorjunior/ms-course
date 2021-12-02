package com.microservice.hroauth.services;

import com.microservice.hroauth.entities.User;
import com.microservice.hroauth.feignclient.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    //INICIO _ para fins didaticos
    public User findByEmail(String email) {
        User user = userFeignClient.findByEmail(email).getBody();
        if (user == null){
            logger.error("Email not found: ".concat(email));
            throw new IllegalArgumentException("Email not found!");
        }
        logger.info("Email found: ".concat(email));
        return user;
    }//FIM _ para fins didaticos

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userFeignClient.findByEmail(username).getBody();
        if (user == null){
            logger.error("Email not found: ".concat(username));
            throw new UsernameNotFoundException("Email not found!");
        }
        logger.info("Email found: ".concat(username));
        return user;
    }
}
