package com.glide.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class StationReportResponse {

    private Long stationId;

    private String stationName;

    private Long totalBikes;

    private Long availableBikes;

    private Long completedRides;

    private BigDecimal totalRevenue;

}