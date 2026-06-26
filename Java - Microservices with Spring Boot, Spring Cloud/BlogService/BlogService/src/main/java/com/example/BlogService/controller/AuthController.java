package com.example.BlogService.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlogService.entity.User;

@RestController
@RequestMapping("/api")
public class AuthController {

	@PostMapping("/register")
    public String register(@RequestBody User user) {
        // save user (service call)
        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public String login() {
        return "Login Successful";
    }
}
