package com.glide.service;

import com.glide.dto.ReservationResponse;

import java.util.List;

public interface ReservationService {

    ReservationResponse reserveBike(Long bikeId);

    List<ReservationResponse> getMyReservations();

    void cancelReservation(Long reservationId);

}