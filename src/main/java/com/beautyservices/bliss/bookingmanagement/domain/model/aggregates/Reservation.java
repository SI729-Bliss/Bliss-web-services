package com.beautyservices.bliss.bookingmanagement.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "reservations")
@Getter
@Setter

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Long serviceId;

    @Column(nullable = false)
    private Long beautySalonId;

    @Column(nullable = false)
    private String reservationDate;

    @Column(nullable = false)
    private String reservationTime;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Float totalPrice;

    @ElementCollection
    @CollectionTable(name = "reservation_customizations", joinColumns = @JoinColumn(name = "reservation_id"))
    @Column(name = "customization")
    private List<String> customizations;
}