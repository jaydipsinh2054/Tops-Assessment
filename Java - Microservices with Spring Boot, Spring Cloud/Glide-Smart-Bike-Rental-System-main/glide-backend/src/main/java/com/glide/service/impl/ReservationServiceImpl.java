package com.glide.service.impl;

import com.glide.dto.ReservationResponse;
import com.glide.entity.Bike;
import com.glide.entity.Reservation;
import com.glide.entity.Station;
import com.glide.entity.User;
import com.glide.entity.Wallet;
import com.glide.enums.BikeStatus;
import com.glide.enums.ReservationStatus;
import com.glide.exception.ResourceNotFoundException;
import com.glide.repository.BikeRepository;
import com.glide.repository.ReservationRepository;
import com.glide.repository.StationRepository;
import com.glide.repository.UserRepository;
import com.glide.repository.WalletRepository;
import com.glide.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final BikeRepository bikeRepository;
    private final UserRepository userRepository;
    private final StationRepository stationRepository;
    private final WalletRepository walletRepository;

    @Override
    public ReservationResponse reserveBike(Long bikeId) {

        User user = getCurrentUser();

        // ===============================
        // Wallet Validation (Minimum ₹50)
        // ===============================
        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Wallet not found."));

        if (wallet.getBalance().compareTo(BigDecimal.valueOf(50)) < 0) {
            throw new IllegalArgumentException(
                    "Minimum wallet balance of ₹50 is required to reserve a bike.");
        }

        Bike bike = bikeRepository.findWithLockById(bikeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bike not found with ID: " + bikeId));

        if (bike.getStatus() != BikeStatus.AVAILABLE) {
            throw new IllegalArgumentException(
                    "Bike is not available for reservation.");
        }

        reservationRepository.findByBikeAndStatus(
                        bike,
                        ReservationStatus.PENDING)
                .ifPresent(reservation -> {
                    throw new IllegalArgumentException(
                            "Bike is already reserved.");
                });

        reservationRepository.findByUserAndStatus(
                        user,
                        ReservationStatus.PENDING)
                .ifPresent(reservation -> {
                    throw new IllegalArgumentException(
                            "You already have an active reservation.");
                });

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setBike(bike);
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setStartTime(LocalDateTime.now());

        bike.setStatus(BikeStatus.RESERVED);

        // Decrease available bike count
        updateStationBikeCount(bike.getStation(), -1);

        bikeRepository.save(bike);

        Reservation savedReservation = reservationRepository.save(reservation);

        return mapToResponse(savedReservation);
    }

    @Override
    public List<ReservationResponse> getMyReservations() {

        User user = getCurrentUser();

        return reservationRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void cancelReservation(Long reservationId) {

        User user = getCurrentUser();

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Reservation not found."));

        if (!reservation.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException(
                    "You cannot cancel another user's reservation.");
        }

        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new IllegalArgumentException(
                    "Reservation is already cancelled.");
        }

        reservation.setStatus(ReservationStatus.CANCELLED);

        Bike bike = reservation.getBike();
        bike.setStatus(BikeStatus.AVAILABLE);

        // Increase available bike count
        updateStationBikeCount(bike.getStation(), 1);

        bikeRepository.save(bike);
        reservationRepository.save(reservation);
    }

    private User getCurrentUser() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));
    }

    /**
     * Updates available bike count.
     */
    private void updateStationBikeCount(Station station, int change) {

        station.setAvailableBikes(
                station.getAvailableBikes() + change);

        stationRepository.save(station);
    }

    /**
     * Converts entity to DTO.
     */
    private ReservationResponse mapToResponse(
            Reservation reservation) {

        return new ReservationResponse(
                reservation.getId(),
                reservation.getBike().getId(),
                reservation.getBike().getBikeNumber(),
                reservation.getBike().getStation().getStationName(),
                reservation.getStatus(),
                reservation.getStartTime(),
                reservation.getEndTime()
        );
    }
}