package com.glide.service;

import com.glide.dto.RideResponse;

import java.util.List;

public interface RideService {

    RideResponse startRide(Long reservationId);

    RideResponse endRide(Long rideId, Long stationId);

    List<RideResponse> getMyRides();

}