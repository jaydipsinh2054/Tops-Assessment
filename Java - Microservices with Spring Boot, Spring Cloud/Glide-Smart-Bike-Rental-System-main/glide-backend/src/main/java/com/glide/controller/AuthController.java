package com.glide.controller;

import com.glide.dto.AuthResponse;
import com.glide.dto.LoginRequest;
import com.glide.dto.RegisterRequest;
import com.glide.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        return authService.register(
                request.getFullName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhone()
        );
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        return authService.login(
                request.getEmail(),
                request.getPassword()
        );
    }
}