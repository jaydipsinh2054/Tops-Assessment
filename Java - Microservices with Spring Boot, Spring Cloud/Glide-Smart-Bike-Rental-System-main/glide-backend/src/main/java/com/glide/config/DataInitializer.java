package com.glide.config;

import com.glide.entity.User;
import com.glide.entity.Wallet;
import com.glide.enums.Role;
import com.glide.repository.UserRepository;
import com.glide.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    private final WalletRepository walletRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.findByEmail("operator@gmail.com").isEmpty()) {

            User operator = User.builder()
                    .fullName("System Operator")
                    .email("operator@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .phone("9999999999")
                    .role(Role.OPERATOR)
                    .build();

            User savedOperator = userRepository.save(operator);

            Wallet wallet = new Wallet();
            wallet.setUser(savedOperator);
            wallet.setBalance(BigDecimal.ZERO);

            walletRepository.save(wallet);

            System.out.println("✅ Default Operator Created");

        }

    }

}