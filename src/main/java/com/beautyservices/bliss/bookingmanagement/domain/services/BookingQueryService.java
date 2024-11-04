package com.beautyservices.bliss.bookingmanagement.domain.services;

import com.beautyservices.bliss.bookingmanagements.domain.model.aggregates.BookingService;

import java.util.Optional;

public interface BookingQueryService {
    Optional<BookingService> findById(Long id);
}