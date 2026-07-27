package com.glide.scheduler;

import com.glide.entity.Bike;
import com.glide.entity.Reservation;
import com.glide.entity.Station;
import com.glide.enums.BikeStatus;
import com.glide.enums.ReservationStatus;
import com.glide.repository.BikeRepository;
import com.glide.repository.ReservationRepository;
import com.glide.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ReservationScheduler {

    private final ReservationRepository reservationRepository;
    private final BikeRepository bikeRepository;
    private final StationRepository stationRepository;

    // Runs every minute
    @Scheduled(fixedRate = 60000)
    public void expireReservations() {

        List<Reservation> reservations =
                reservationRepository.findByStatus(
                        ReservationStatus.PENDING);

        LocalDateTime now = LocalDateTime.now();

        for (Reservation reservation : reservations) {

            if (reservation.getStartTime()
                    .plusMinutes(10)
                    .isBefore(now)) {

                Bike bike = reservation.getBike();

                bike.setStatus(BikeStatus.AVAILABLE);
                bikeRepository.save(bike);

                Station station = bike.getStation();

                station.setAvailableBikes(
                        station.getAvailableBikes() + 1);

                stationRepository.save(station);

                reservation.setStatus(
                        ReservationStatus.CANCELLED);

                reservation.setEndTime(now);

                reservationRepository.save(reservation);

                log.info(
                        "Reservation {} expired automatically.",
                        reservation.getId());
            }
        }
    }
}