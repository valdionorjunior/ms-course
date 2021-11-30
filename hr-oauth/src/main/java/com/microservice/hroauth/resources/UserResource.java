package com.microservice.hroauth.resources;

import com.microservice.hroauth.entities.User;
import com.microservice.hroauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping(value = "/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email){//querie parameter .../search?Email=test@email.com
        try {
        User user = service.findByEmail(email);
        return ResponseEntity.ok(user);
        }catch (IllegalArgumentException exception){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
