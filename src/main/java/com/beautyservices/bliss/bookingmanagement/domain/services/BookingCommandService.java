package com.beautyservices.bliss.bookingmanagement.domain.services;

import com.beautyservices.bliss.bookingmanagements.domain.model.aggregates.BookingService;
import com.beautyservices.bliss.bookingmanagements.domain.model.commands.CreateBookingCommand;
import com.beautyservices.bliss.bookingmanagements.domain.model.commands.UpdateBookingCommand;
import com.beautyservices.bliss.bookingmanagements.domain.model.commands.DeleteBookingCommand;

import java.util.Optional;

public interface BookingCommandService {
    BookingService handle(CreateBookingCommand command);
    Optional<BookingService> handle(UpdateBookingCommand command);
    void handle(DeleteBookingCommand command);
}