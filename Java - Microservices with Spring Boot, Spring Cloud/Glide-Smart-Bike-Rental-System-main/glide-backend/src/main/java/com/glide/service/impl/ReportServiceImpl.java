package com.glide.service.impl;

import com.glide.dto.StationReportResponse;
import com.glide.entity.Bike;
import com.glide.entity.Ride;
import com.glide.entity.Station;
import com.glide.enums.RideStatus;
import com.glide.repository.BikeRepository;
import com.glide.repository.RideRepository;
import com.glide.repository.StationRepository;
import com.glide.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final StationRepository stationRepository;

    private final BikeRepository bikeRepository;

    private final RideRepository rideRepository;

    @Override
    public List<StationReportResponse> getStationReports() {

        List<StationReportResponse> reports = new ArrayList<>();

        List<Station> stations = stationRepository.findAll();

        List<Ride> rides = rideRepository.findAll();

        for (Station station : stations) {

            List<Bike> bikes =
                    bikeRepository.findByStationId(station.getId());

            long totalBikes = bikes.size();

            long availableBikes =
                    bikes.stream()
                            .filter(bike ->
                                    bike.getStatus().name().equals("AVAILABLE"))
                            .count();

            long completedRides = 0;

            BigDecimal revenue = BigDecimal.ZERO;

            for (Ride ride : rides) {

                if (ride.getStatus() != RideStatus.COMPLETED)
                    continue;

                if (ride.getBike() == null)
                    continue;

                if (ride.getBike().getStation() == null)
                    continue;

                if (!ride.getBike()
                        .getStation()
                        .getId()
                        .equals(station.getId()))
                    continue;

                completedRides++;

                if (ride.getFare() != null)
                    revenue = revenue.add(ride.getFare());

            }

            reports.add(

                    new StationReportResponse(

                            station.getId(),

                            station.getStationName(),

                            totalBikes,

                            availableBikes,

                            completedRides,

                            revenue

                    )

            );

        }

        return reports;

    }

}