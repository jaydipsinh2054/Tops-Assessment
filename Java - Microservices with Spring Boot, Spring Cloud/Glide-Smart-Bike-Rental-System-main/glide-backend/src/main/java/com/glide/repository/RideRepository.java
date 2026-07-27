package com.glide.repository;

import com.glide.entity.Ride;
import com.glide.entity.User;
import com.glide.enums.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;

public interface RideRepository extends JpaRepository<Ride, Long> {

    List<Ride> findByUser(User user);

    Optional<Ride> findByUserAndStatus(
            User user,
            RideStatus status);

    Long countByStatus(RideStatus status);

    @Query("""
           SELECT COALESCE(SUM(r.fare),0)
           FROM Ride r
           WHERE r.status='COMPLETED'
           """)
    BigDecimal getTotalRevenue();

    List<Ride> findAll();

}