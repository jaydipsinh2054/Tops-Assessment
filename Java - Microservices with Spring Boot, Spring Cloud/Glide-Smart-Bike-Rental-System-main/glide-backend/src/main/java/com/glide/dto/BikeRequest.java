package com.glide.dto;

import com.glide.enums.BikeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BikeRequest {

    private String bikeNumber;

    private String model;

    private BikeStatus status;

    private Long stationId;
}