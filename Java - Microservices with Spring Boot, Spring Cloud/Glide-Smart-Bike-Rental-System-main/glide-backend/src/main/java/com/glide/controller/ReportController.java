package com.glide.controller;

import com.glide.dto.StationReportResponse;
import com.glide.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/stations")
    public ResponseEntity<List<StationReportResponse>>
    getStationReports() {

        return ResponseEntity.ok(

                reportService.getStationReports()

        );

    }

}