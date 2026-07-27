package com.glide.dto;

import com.glide.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RideResponse {

    private Long rideId;

    private Long reservationId;

    private Long bikeId;

    private String bikeNumber;

    private String stationName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double distance;

    private BigDecimal fare;

    private RideStatus status;

}