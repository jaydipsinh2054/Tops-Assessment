package com.glide.controller;

import com.glide.dto.RideResponse;
import com.glide.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;

    @PostMapping("/start/{reservationId}")
    public ResponseEntity<RideResponse> startRide(
            @PathVariable Long reservationId) {

        RideResponse response =
                rideService.startRide(reservationId);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED);
    }

    @GetMapping("/my")
    public ResponseEntity<List<RideResponse>> getMyRides() {

        return ResponseEntity.ok(
                rideService.getMyRides());
    }
    @PostMapping("/end/{rideId}")
    public ResponseEntity<RideResponse> endRide(
            @PathVariable Long rideId,
            @RequestParam Long stationId) {

        return ResponseEntity.ok(
                rideService.endRide(rideId, stationId));
    }

}