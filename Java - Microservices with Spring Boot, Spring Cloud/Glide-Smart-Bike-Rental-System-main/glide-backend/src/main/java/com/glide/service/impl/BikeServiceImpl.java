package com.glide.service.impl;

import com.glide.dto.BikeRequest;
import com.glide.dto.BikeResponse;
import com.glide.entity.Bike;
import com.glide.entity.Station;
import com.glide.enums.BikeStatus;
import com.glide.exception.ResourceNotFoundException;
import com.glide.repository.BikeRepository;
import com.glide.repository.StationRepository;
import com.glide.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BikeServiceImpl implements BikeService {

    private final BikeRepository bikeRepository;
    private final StationRepository stationRepository;

    @Override
    public BikeResponse addBike(BikeRequest request) {

        bikeRepository.findByBikeNumber(request.getBikeNumber())
                .ifPresent(bike -> {
                    throw new IllegalArgumentException("Bike number already exists.");
                });

        Station station = stationRepository.findById(request.getStationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Station not found with ID: " + request.getStationId()));

        Bike bike = new Bike();
        bike.setBikeNumber(request.getBikeNumber());
        bike.setModel(request.getModel());
        bike.setStatus(request.getStatus());
        bike.setStation(station);

        Bike savedBike = bikeRepository.save(bike);

        // Update available bike count only if the bike is available
        if (savedBike.getStatus() == BikeStatus.AVAILABLE) {
            station.setAvailableBikes(station.getAvailableBikes() + 1);
            stationRepository.save(station);
        }

        return mapToResponse(savedBike);
    }

    @Override
    public List<BikeResponse> getAllBikes() {

        return bikeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public BikeResponse getBikeById(Long id) {

        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bike not found with ID: " + id));

        return mapToResponse(bike);
    }

    @Override
    public BikeResponse updateBike(Long id, BikeRequest request) {

        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bike not found with ID: " + id));

        bikeRepository.findByBikeNumber(request.getBikeNumber())
                .ifPresent(existingBike -> {
                    if (!existingBike.getId().equals(id)) {
                        throw new IllegalArgumentException("Bike number already exists.");
                    }
                });

        Station station = stationRepository.findById(request.getStationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Station not found with ID: " + request.getStationId()));

        bike.setBikeNumber(request.getBikeNumber());
        bike.setModel(request.getModel());
        bike.setStatus(request.getStatus());
        bike.setStation(station);

        Bike updatedBike = bikeRepository.save(bike);

        return mapToResponse(updatedBike);
    }

    @Override
    public void deleteBike(Long id) {

        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bike not found with ID: " + id));

        Station station = bike.getStation();

        // Decrease available bike count only if the bike was available
        if (bike.getStatus() == BikeStatus.AVAILABLE) {
            station.setAvailableBikes(station.getAvailableBikes() - 1);
            stationRepository.save(station);
        }

        bikeRepository.delete(bike);
    }

    @Override
    public List<BikeResponse> getBikesByStation(Long stationId) {

        stationRepository.findById(stationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Station not found with ID: " + stationId));

        return bikeRepository.findByStationId(stationId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Convert Bike Entity to BikeResponse DTO
     */
    private BikeResponse mapToResponse(Bike bike) {

        return new BikeResponse(
                bike.getId(),
                bike.getBikeNumber(),
                bike.getModel(),
                bike.getStatus(),
                bike.getStation().getId(),
                bike.getStation().getStationName()
        );
    }
}