package com.glide.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DashboardResponse {

    private Long totalUsers;

    private Long totalStations;

    private Long totalBikes;

    private Long availableBikes;

    private Long activeRides;

    private BigDecimal totalRevenue;

}