package com.glide.service.impl;

import com.glide.dto.RiderDashboardResponse;
import com.glide.entity.User;
import com.glide.entity.Wallet;
import com.glide.enums.ReservationStatus;
import com.glide.enums.RideStatus;
import com.glide.exception.ResourceNotFoundException;
import com.glide.repository.*;
import com.glide.service.RiderDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RiderDashboardServiceImpl
        implements RiderDashboardService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final RideRepository rideRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public RiderDashboardResponse getDashboard() {

        User user = getCurrentUser();

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Wallet not found."));

        boolean activeRide =
                rideRepository.findByUserAndStatus(
                        user,
                        RideStatus.STARTED
                ).isPresent();

        boolean activeReservation =
                reservationRepository.findByUserAndStatus(
                        user,
                        ReservationStatus.PENDING
                ).isPresent();

        long completedRides =
                rideRepository.findByUser(user)
                        .stream()
                        .filter(r -> r.getStatus() == RideStatus.COMPLETED)
                        .count();

        BigDecimal totalSpent =
                rideRepository.findByUser(user)
                        .stream()
                        .filter(r -> r.getFare() != null)
                        .map(r -> r.getFare())
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .add(BigDecimal.valueOf(completedRides * 5));

        return new RiderDashboardResponse(

                wallet.getBalance(),

                activeRide,

                activeReservation,

                completedRides,

                totalSpent

        );

    }

    private User getCurrentUser() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

    }

}