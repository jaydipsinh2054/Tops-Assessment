package com.glide.service;

import com.glide.dto.StationRequest;
import com.glide.dto.StationResponse;

import java.util.List;

public interface StationService {

    StationResponse addStation(StationRequest request);

    List<StationResponse> getAllStations();

    StationResponse getStationById(Long id);

    StationResponse updateStation(Long id, StationRequest request);

    void deleteStation(Long id);
}