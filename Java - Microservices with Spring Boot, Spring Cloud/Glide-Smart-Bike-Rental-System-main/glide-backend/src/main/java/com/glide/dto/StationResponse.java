package com.glide.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StationResponse {

    private Long id;

    private String stationName;

    private String address;

    private int totalSlots;

    private int availableBikes;
}