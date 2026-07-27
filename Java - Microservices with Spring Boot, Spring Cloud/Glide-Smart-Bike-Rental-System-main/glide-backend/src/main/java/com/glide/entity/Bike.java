package com.glide.entity;

import com.glide.enums.BikeStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String bikeNumber;

    private String model;

    @Enumerated(EnumType.STRING)
    private BikeStatus status;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
}