package com.glide.service;

import com.glide.dto.BikeRequest;
import com.glide.dto.BikeResponse;

import java.util.List;

public interface BikeService {

    BikeResponse addBike(BikeRequest request);

    List<BikeResponse> getAllBikes();

    BikeResponse getBikeById(Long id);

    BikeResponse updateBike(Long id, BikeRequest request);

    void deleteBike(Long id);

    List<BikeResponse> getBikesByStation(Long stationId);
}