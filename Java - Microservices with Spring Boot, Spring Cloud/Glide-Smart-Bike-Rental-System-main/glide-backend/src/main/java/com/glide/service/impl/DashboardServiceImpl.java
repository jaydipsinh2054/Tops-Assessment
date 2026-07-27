package com.glide.service.impl;

import com.glide.dto.DashboardResponse;
import com.glide.enums.BikeStatus;
import com.glide.enums.RideStatus;
import com.glide.repository.BikeRepository;
import com.glide.repository.RideRepository;
import com.glide.repository.StationRepository;
import com.glide.repository.UserRepository;
import com.glide.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final StationRepository stationRepository;
    private final BikeRepository bikeRepository;
    private final RideRepository rideRepository;

    @Override
    public DashboardResponse getDashboard() {

        return new DashboardResponse(

                userRepository.count(),

                stationRepository.count(),

                bikeRepository.count(),

                bikeRepository.countByStatus(BikeStatus.AVAILABLE),

                rideRepository.countByStatus(RideStatus.STARTED),

                rideRepository.getTotalRevenue()

        );
    }
}