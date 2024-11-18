package com.beautyservices.bliss.bookingmanagement.domain.services;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import com.beautyservices.bliss.bookingmanagement.domain.model.commands.CreateBookingCommand;
import com.beautyservices.bliss.bookingmanagement.domain.model.commands.UpdateBookingCommand;
import com.beautyservices.bliss.bookingmanagement.domain.model.commands.DeleteBookingCommand;

import java.util.Optional;

public interface BookingCommandService {
    Optional<Reservation> handle(CreateBookingCommand command);
    Optional<Reservation> handle(UpdateBookingCommand command);
    void handle(DeleteBookingCommand command);
}