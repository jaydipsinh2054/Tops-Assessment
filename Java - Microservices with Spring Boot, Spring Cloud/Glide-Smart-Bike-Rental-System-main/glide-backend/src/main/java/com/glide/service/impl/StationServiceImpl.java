package com.glide.service.impl;

import com.glide.dto.StationRequest;
import com.glide.dto.StationResponse;
import com.glide.entity.Station;
import com.glide.exception.ResourceNotFoundException;
import com.glide.repository.StationRepository;
import com.glide.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    @Override
    public StationResponse addStation(StationRequest request) {


        Station station = new Station();
        station.setStationName(request.getStationName());
        station.setAddress(request.getAddress());
        station.setTotalSlots(request.getTotalSlots());
        station.setAvailableBikes(0);

        Station savedStation = stationRepository.save(station);

        return mapToResponse(savedStation);
    }

    @Override
    public List<StationResponse> getAllStations() {

        return stationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public StationResponse getStationById(Long id) {

        Station station = stationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Station not found with ID: " + id));

        return mapToResponse(station);
    }

    @Override
    public StationResponse updateStation(Long id, StationRequest request) {


        Station station = stationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Station not found with ID: " + id));

        station.setStationName(request.getStationName());
        station.setAddress(request.getAddress());
        station.setTotalSlots(request.getTotalSlots());

        Station updatedStation = stationRepository.save(station);

        return mapToResponse(updatedStation);
    }

    @Override
    public void deleteStation(Long id) {

        Station station = stationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Station not found with ID: " + id));

        stationRepository.delete(station);
    }

    /**
     * Converts Station Entity to StationResponse DTO.
     */
    private StationResponse mapToResponse(Station station) {

        return new StationResponse(
                station.getId(),
                station.getStationName(),
                station.getAddress(),
                station.getTotalSlots(),
                station.getAvailableBikes()
        );
    }
}