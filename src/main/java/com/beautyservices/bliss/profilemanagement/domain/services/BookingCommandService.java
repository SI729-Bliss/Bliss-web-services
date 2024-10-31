package com.beautyservices.bliss.profilemanagement.domain.services;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.BookingService;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.CreateBookingCommand;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.UpdateBookingCommand;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.DeleteBookingCommand;

import java.util.Optional;

public interface BookingCommandService {
    BookingService handle(CreateBookingCommand command);
    Optional<BookingService> handle(UpdateBookingCommand command);
    void handle(DeleteBookingCommand command);
}