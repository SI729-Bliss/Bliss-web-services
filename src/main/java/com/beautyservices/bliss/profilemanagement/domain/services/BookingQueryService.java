package com.beautyservices.bliss.profilemanagement.domain.services;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.BookingService;
import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetBookingByIdQuery;

import java.util.Optional;

public interface BookingQueryService {
    Optional<BookingService> handle(GetBookingByIdQuery query);

    Object findById(Long id);
}