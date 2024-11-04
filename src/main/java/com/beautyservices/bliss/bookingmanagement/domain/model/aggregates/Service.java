package com.beautyservices.bliss.bookingmanagement.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "BookingServiceEntity")
@Table(name = "services")
@Getter
@Setter
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String language;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String description;

    @Column(name = "base_price", nullable = false)
    private Double basePrice;

    @Column(nullable = false)
    private String beautySalon;

    @Column(nullable = false)
    private Integer sales;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private String image;

    @Column(name = "beauty_salon_id", nullable = false)
    private Long beautySalonId;
}