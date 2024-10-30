package com.sarka.auction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController{

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/admin")
    public ResponseEntity<Map<String, Object>> sayHelloToAdmin(@AuthenticationPrincipal Jwt jwt) {
        //here we return the response as info about the user, especially their SUB
        String userId = jwt.getSubject();
        String username = jwt.getClaimAsString("preferred_username");
        String email = jwt.getClaimAsString("email");


        // Create a response map
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello, Admin!");
        response.put("userId", userId);
        response.put("username", username);
        response.put("email", email);

        // Return response wrapped in ResponseEntity
        return ResponseEntity.ok(response);


    }

    @GetMapping("/user")

    public  ResponseEntity<Map<String, Object>>  sayHelloToUser(@AuthenticationPrincipal Jwt jwt, Integer bid) {
        String username = jwt.getClaimAsString("preferred_username");
        String userId = jwt.getSubject();
        Map<String, Object> response = new HashMap<>();
        bid = 1000;
        response.put("username", username);
        response.put("userId", userId);
        response.put("bidAmount", bid);
        return ResponseEntity.ok(response);
    }
}