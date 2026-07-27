package com.glide.service.impl;

import com.glide.dto.AuthResponse;
import com.glide.entity.User;
import com.glide.entity.Wallet;
import com.glide.enums.Role;
import com.glide.repository.UserRepository;
import com.glide.repository.WalletRepository;
import com.glide.security.JwtService;
import com.glide.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final WalletRepository walletRepository;

    @Override
    public String register(String fullName,
                           String email,
                           String password,
                           String phone) {

        if (userRepository.findByEmail(email).isPresent()) {
            return "Email already exists";
        }

        User user = User.builder()
                .fullName(fullName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .phone(phone)
                .role(Role.RIDER)
                .build();

        User savedUser = userRepository.save(user);

        Wallet wallet = new Wallet();
        wallet.setUser(savedUser);
        wallet.setBalance(BigDecimal.ZERO);

        walletRepository.save(wallet);

        return "Registration Successful";
    }

    @Override
    public AuthResponse login(String email, String password) {

        System.out.println("====================================");
        System.out.println("LOGIN EMAIL = [" + email + "]");
        System.out.println("EMAIL LENGTH = " + email.length());

        boolean exists = userRepository.findByEmail(email).isPresent();

        System.out.println("USER EXISTS = " + exists);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("DB EMAIL = " + user.getEmail());
        System.out.println("DB ROLE = " + user.getRole());

        if (!passwordEncoder.matches(password, user.getPassword())) {

            System.out.println("PASSWORD MATCH = FALSE");

            throw new RuntimeException("Invalid Password");
        }

        System.out.println("PASSWORD MATCH = TRUE");

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                user.getFullName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}