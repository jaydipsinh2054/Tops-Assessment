package com.glide.controller;

import com.glide.dto.RiderDashboardResponse;
import com.glide.service.RiderDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rider/dashboard")
@RequiredArgsConstructor
public class RiderDashboardController {

    private final RiderDashboardService riderDashboardService;

    @GetMapping
    public ResponseEntity<RiderDashboardResponse> getDashboard() {

        return ResponseEntity.ok(
                riderDashboardService.getDashboard());

    }

}