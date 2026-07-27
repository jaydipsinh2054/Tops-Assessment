package com.glide.service;

import com.glide.dto.AuthResponse;

public interface AuthService {

    String register(String fullName,
                    String email,
                    String password,
                    String phone);

    AuthResponse login(String email,
                       String password);

}