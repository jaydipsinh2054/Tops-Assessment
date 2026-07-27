package com.glide.controller;

import com.glide.dto.ReservationResponse;
import com.glide.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/{bikeId}")
    public ResponseEntity<ReservationResponse> reserveBike(
            @PathVariable Long bikeId) {

        ReservationResponse response =
                reservationService.reserveBike(bikeId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/my")
    public ResponseEntity<List<ReservationResponse>> getMyReservations() {

        return ResponseEntity.ok(
                reservationService.getMyReservations());
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> cancelReservation(
            @PathVariable Long reservationId) {

        reservationService.cancelReservation(reservationId);

        return ResponseEntity.ok(
                "Reservation cancelled successfully.");
    }
}