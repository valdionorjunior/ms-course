package com.microservice.hroauth.feignclient;

import com.microservice.hroauth.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name= "hr-user", path = "/users")
public interface UserFeignClient {

    @GetMapping(value = "/search")//busca por querye paramiteres ex /search?email=valdionorjunior@outlook.com
    ResponseEntity<User> findByEmail(@RequestParam String email);

}
