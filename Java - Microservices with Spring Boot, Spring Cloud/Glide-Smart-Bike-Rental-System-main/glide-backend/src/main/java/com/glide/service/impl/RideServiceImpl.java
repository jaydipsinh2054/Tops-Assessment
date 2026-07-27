package com.glide.service.impl;

import com.glide.dto.RideResponse;
import com.glide.entity.*;
import com.glide.enums.BikeStatus;
import com.glide.enums.ReservationStatus;
import com.glide.enums.RideStatus;
import com.glide.enums.TransactionType;
import com.glide.exception.ResourceNotFoundException;
import com.glide.repository.*;
import com.glide.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BikeRepository bikeRepository;
    private final WalletRepository walletRepository;
    private final WalletTransactionRepository walletTransactionRepository;
    private final StationRepository stationRepository;
    private static final BigDecimal UNLOCK_FEE = BigDecimal.valueOf(5);
    private static final BigDecimal FARE_PER_MINUTE = BigDecimal.valueOf(2);

    @Override
    public RideResponse startRide(Long reservationId) {

        User user = getCurrentUser();

        Reservation reservation = reservationRepository
                .findByIdAndUser(reservationId, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Reservation not found."));

        if (reservation.getStatus() != ReservationStatus.PENDING) {
            throw new IllegalArgumentException(
                    "Reservation is not active.");
        }

        rideRepository.findByUserAndStatus(
                        user,
                        RideStatus.STARTED)
                .ifPresent(ride -> {
                    throw new IllegalArgumentException(
                            "You already have an active ride.");
                });

        Bike bike = reservation.getBike();

        if (bike.getStatus() != BikeStatus.RESERVED) {
            throw new IllegalArgumentException(
                    "Bike is not reserved.");
        }

        // ===========================
        // Deduct ₹5 Unlock Fee
        // ===========================

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Wallet not found."));

        wallet.setBalance(wallet.getBalance().subtract(UNLOCK_FEE));
        walletRepository.save(wallet);

        WalletTransaction unlockTransaction = new WalletTransaction();
        unlockTransaction.setWallet(wallet);
        unlockTransaction.setAmount(UNLOCK_FEE);
        unlockTransaction.setType(TransactionType.DEBIT);

        walletTransactionRepository.save(unlockTransaction);

        bike.setStatus(BikeStatus.IN_USE);
        bikeRepository.save(bike);

        reservation.setStatus(ReservationStatus.COMPLETED);
        reservationRepository.save(reservation);

        Ride ride = new Ride();
        ride.setReservation(reservation);
        ride.setUser(user);
        ride.setBike(bike);
        ride.setStartTime(LocalDateTime.now());
        ride.setStatus(RideStatus.STARTED);

        Ride savedRide = rideRepository.save(ride);

        return mapToResponse(savedRide);
    }

    @Override
    public RideResponse endRide(Long rideId, Long stationId) {

        User user = getCurrentUser();

        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ride not found."));

        if (!ride.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Unauthorized access.");
        }

        if (ride.getStatus() != RideStatus.STARTED) {
            throw new IllegalArgumentException("Ride is already completed.");
        }

        ride.setEndTime(LocalDateTime.now());

        long minutes = Duration
                .between(ride.getStartTime(), ride.getEndTime())
                .toMinutes();

        if (minutes <= 0) {
            minutes = 1;
        }

        BigDecimal fare = FARE_PER_MINUTE.multiply(
                BigDecimal.valueOf(minutes));

        ride.setFare(fare);
        ride.setStatus(RideStatus.COMPLETED);

        // Deduct ride fare
        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Wallet not found."));

        wallet.setBalance(wallet.getBalance().subtract(fare));
        walletRepository.save(wallet);

        WalletTransaction transaction = new WalletTransaction();
        transaction.setWallet(wallet);
        transaction.setAmount(fare);
        transaction.setType(TransactionType.DEBIT);

        walletTransactionRepository.save(transaction);

        // Find destination station
        Station destinationStation = stationRepository.findById(stationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Station not found."));

        Bike bike = ride.getBike();

        // Move bike to new station
        bike.setStation(destinationStation);

        bike.setStatus(BikeStatus.AVAILABLE);

        bikeRepository.save(bike);

        // Increase bikes at destination station
        destinationStation.setAvailableBikes(
                destinationStation.getAvailableBikes() + 1);

        stationRepository.save(destinationStation);

        Ride savedRide = rideRepository.save(ride);

        return mapToResponse(savedRide);
    }

    @Override
    public List<RideResponse> getMyRides() {

        User user = getCurrentUser();

        return rideRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private User getCurrentUser() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found."));
    }

    private RideResponse mapToResponse(Ride ride) {

        return new RideResponse(
                ride.getId(),
                ride.getReservation().getId(),
                ride.getBike().getId(),
                ride.getBike().getBikeNumber(),
                ride.getBike().getStation().getStationName(),
                ride.getStartTime(),
                ride.getEndTime(),
                ride.getDistance(),
                ride.getFare(),
                ride.getStatus()
        );
    }
}