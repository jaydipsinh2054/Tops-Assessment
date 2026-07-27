package com.glide.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RiderDashboardResponse {

    private BigDecimal walletBalance;

    private boolean activeRide;

    private boolean activeReservation;

    private Long completedRides;

    private BigDecimal totalSpent;

}