package com.glide.repository;

import com.glide.entity.Bike;
import com.glide.entity.Reservation;
import com.glide.entity.User;
import com.glide.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUser(User user);

    Optional<Reservation> findByBikeAndStatus(
            Bike bike,
            ReservationStatus status);

    Optional<Reservation> findByUserAndStatus(
            User user,
            ReservationStatus status);

    Optional<Reservation> findByIdAndUser(
            Long id,
            User user);

    List<Reservation> findByStatus(ReservationStatus status);

}