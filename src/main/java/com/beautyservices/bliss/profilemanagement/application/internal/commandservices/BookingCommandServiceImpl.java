package com.beautyservices.bliss.profilemanagement.application.internal.commandservices;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.BookingService;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.CreateBookingCommand;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.DeleteBookingCommand;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.UpdateBookingCommand;
import com.beautyservices.bliss.profilemanagement.domain.services.BookingCommandService;
import com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingCommandServiceImpl implements BookingCommandService {

    private final BookingRepository bookingRepository;

    public BookingCommandServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingService handle(CreateBookingCommand command) {
        BookingService booking = new BookingService();
        booking.setCustomerId(command.customerId());
        booking.setServiceId(command.serviceId());
        booking.setDate(command.date());
        booking.setTime(command.time());
        booking.setStatus(command.status());
        booking.setFullName(command.fullName());
        booking.setEmail(command.email());
        booking.setService(command.service());
        booking.setAvailability(command.availability());
        booking.setMessage(String.valueOf(command.message()));
        booking.setRequirements(command.requirements());
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<BookingService> handle(UpdateBookingCommand command) {
        var bookingId = command.id();

        if (!bookingRepository.existsById((Long) bookingId)) {
            throw new IllegalArgumentException("Booking with id " + bookingId + " does not exist");
        }

        var bookingToUpdate = bookingRepository.findById((Long) bookingId).get();
        bookingToUpdate.setCustomerId(command.customerId());
        bookingToUpdate.setServiceId(command.serviceId());
        bookingToUpdate.setDate(command.date());
        bookingToUpdate.setTime(command.time());
        bookingToUpdate.setStatus(command.status());
        bookingToUpdate.setFullName(command.fullName());
        bookingToUpdate.setEmail(command.email());
        bookingToUpdate.setService(command.service());
        bookingToUpdate.setAvailability(command.availability());
        bookingToUpdate.setMessage(String.valueOf(command.message()));
        bookingToUpdate.setRequirements(command.requirements());

        try {
            var updatedBooking = bookingRepository.save(bookingToUpdate);
            return Optional.of(updatedBooking);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating booking: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteBookingCommand command) {
        var bookingId = command.id();

        if (!bookingRepository.existsById(bookingId)) {
            throw new IllegalArgumentException("Booking with id " + bookingId + " does not exist");
        }

        bookingRepository.deleteById(bookingId);
    }
}