package com.beautyservices.bliss.bookingmanagement.domain.services;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import com.beautyservices.bliss.bookingmanagement.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface BookingQueryService {
    Optional<Reservation> handle(GetReservationByIdQuery query);
    List<Reservation> handle(GetReservationsByCustomerIdQuery query);
    List<Reservation> handle(GetReservationsByServiceIdQuery query);
    List<Reservation> handle(GetAllReservationsQuery query);
    List<Reservation> handle(GetReservationsByCompanyIdQuery query);
}