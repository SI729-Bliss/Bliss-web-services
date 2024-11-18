package com.beautyservices.bliss.bookingmanagement.domain.services;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import com.beautyservices.bliss.bookingmanagement.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface BookingQueryService {
    Optional<Reservation> handle(GetBookingByIdQuery query);
    List<Reservation> handle(GetBookingsByCustomerIdQuery query);
    List<Reservation> handle(GetBookingsByServiceIdQuery query);
    List<Reservation> handle(GetAllBookingsQuery query);
    List<Reservation> handle(GetBookingsByCompanyIdQuery query);
}