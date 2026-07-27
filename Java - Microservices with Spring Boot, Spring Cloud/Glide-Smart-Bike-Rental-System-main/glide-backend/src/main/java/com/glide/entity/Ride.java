package com.glide.entity;

import com.glide.enums.RideStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Reservation reservation;

    @ManyToOne
    private User user;

    @ManyToOne
    private Bike bike;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double distance;

    private BigDecimal fare;

    @Enumerated(EnumType.STRING)
    private RideStatus status;
}