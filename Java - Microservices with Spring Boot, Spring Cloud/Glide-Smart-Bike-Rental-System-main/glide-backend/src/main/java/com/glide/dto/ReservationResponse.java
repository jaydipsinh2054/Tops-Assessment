package com.glide.dto;

import com.glide.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {

    private Long reservationId;

    private Long bikeId;

    private String bikeNumber;

    private String stationName;

    private ReservationStatus status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}