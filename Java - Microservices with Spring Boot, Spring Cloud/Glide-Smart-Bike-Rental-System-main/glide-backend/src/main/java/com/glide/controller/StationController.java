package com.glide.controller;

import com.glide.dto.StationRequest;
import com.glide.dto.StationResponse;
import com.glide.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    /**
     * Add a new station
     */
    @PostMapping
    public ResponseEntity<StationResponse> addStation(@RequestBody StationRequest request) {

        StationResponse response = stationService.addStation(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Get all stations
     */
    @GetMapping
    public ResponseEntity<List<StationResponse>> getAllStations() {

        return ResponseEntity.ok(stationService.getAllStations());
    }

    /**
     * Get station by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<StationResponse> getStationById(@PathVariable Long id) {

        return ResponseEntity.ok(stationService.getStationById(id));
    }

    /**
     * Update station
     */
    @PutMapping("/{id}")
    public ResponseEntity<StationResponse> updateStation(
            @PathVariable Long id,
            @RequestBody StationRequest request) {

        return ResponseEntity.ok(stationService.updateStation(id, request));
    }

    /**
     * Delete station
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStation(@PathVariable Long id) {

        stationService.deleteStation(id);

        return ResponseEntity.ok("Station deleted successfully.");
    }
}