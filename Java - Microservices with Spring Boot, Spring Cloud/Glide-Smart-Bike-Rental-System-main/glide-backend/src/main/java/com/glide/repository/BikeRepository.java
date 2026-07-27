package com.glide.repository;

import com.glide.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import com.glide.enums.BikeStatus;

public interface BikeRepository extends JpaRepository<Bike, Long> {

    Optional<Bike> findByBikeNumber(String bikeNumber);

    List<Bike> findByStationId(Long stationId);
    Long countByStatus(BikeStatus status);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Bike> findWithLockById(Long id);
}