package com.glide.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // ==========================
                        // Public APIs
                        // ==========================
                        .requestMatchers("/api/auth/**")
                        .permitAll()

                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // ==========================
                        // Public Read APIs
                        // ==========================
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/stations/**",
                                "/api/bikes/**"
                        ).permitAll()

                        // ==========================
                        // Operator Only
                        // ==========================
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/stations/**",
                                "/api/bikes/**"
                        ).hasRole("OPERATOR")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/stations/**",
                                "/api/bikes/**"
                        ).hasRole("OPERATOR")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/stations/**",
                                "/api/bikes/**"
                        ).hasRole("OPERATOR")

                        .requestMatchers("/api/dashboard/**")
                        .hasRole("OPERATOR")

                        // ==========================
                        // Rider & Operator
                        // ==========================
                        .requestMatchers(
                                "/api/wallet/**",
                                "/api/reservations/**",
                                "/api/rides/**"
                        )
                        .hasAnyRole("RIDER", "OPERATOR")

                        .anyRequest()
                        .authenticated()

                )

                .userDetailsService(customUserDetailsService)

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }
}