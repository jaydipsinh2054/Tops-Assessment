package com.glide.controller;

import com.glide.dto.BikeRequest;
import com.glide.dto.BikeResponse;
import com.glide.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bikes")
@RequiredArgsConstructor
public class BikeController {

    private final BikeService bikeService;

    /**
     * Add Bike
     */
    @PostMapping
    public ResponseEntity<BikeResponse> addBike(@RequestBody BikeRequest request) {

        BikeResponse response = bikeService.addBike(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Get All Bikes
     */
    @GetMapping
    public ResponseEntity<List<BikeResponse>> getAllBikes() {

        return ResponseEntity.ok(bikeService.getAllBikes());
    }

    /**
     * Get Bike By ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<BikeResponse> getBikeById(@PathVariable Long id) {

        return ResponseEntity.ok(bikeService.getBikeById(id));
    }

    /**
     * Update Bike
     */
    @PutMapping("/{id}")
    public ResponseEntity<BikeResponse> updateBike(
            @PathVariable Long id,
            @RequestBody BikeRequest request) {

        return ResponseEntity.ok(
                bikeService.updateBike(id, request));
    }

    /**
     * Delete Bike
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBike(@PathVariable Long id) {

        bikeService.deleteBike(id);
        return ResponseEntity.ok("Bike deleted successfully.");
    }

    /**
     * Get Bikes By Station
     */
    @GetMapping("/station/{stationId}")
    public ResponseEntity<List<BikeResponse>> getBikesByStation(
            @PathVariable Long stationId) {

        return ResponseEntity.ok(
                bikeService.getBikesByStation(stationId));
    }
}