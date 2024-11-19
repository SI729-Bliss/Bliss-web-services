package com.beautyservices.bliss.bookingmanagement.application.internal.commandservices;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import com.beautyservices.bliss.bookingmanagement.domain.model.commands.CreateBookingCommand;
import com.beautyservices.bliss.bookingmanagement.domain.model.commands.UpdateBookingCommand;
import com.beautyservices.bliss.bookingmanagement.domain.model.commands.DeleteBookingCommand;
import com.beautyservices.bliss.bookingmanagement.domain.services.BookingCommandService;
import com.beautyservices.bliss.bookingmanagement.infrastructure.persistence.jpa.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingCommandServiceImpl implements BookingCommandService {


    private final BookingRepository reservationRepository;

    public BookingCommandServiceImpl(BookingRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @Override
    public Long handle(CreateBookingCommand command) {
        Reservation reservation = new Reservation(command);
        try{
            this.reservationRepository.save(reservation);
        }catch(Exception e){
            throw new IllegalArgumentException("Error while saving payment: " + e.getMessage());
        }
        return reservation.getId() ;
    }

    @Override
    public Optional<Reservation> handle(UpdateBookingCommand command) {
        var reservationId = command.id();
        var reservationToUpdate = this.reservationRepository.findById(reservationId).orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        reservationToUpdate.update(command);

        try {
            var updatedReservation = this.reservationRepository.save(reservationToUpdate);
            return Optional.of(updatedReservation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating reservation: " + e.getMessage());
        }
    }


    @Override
    public void handle(DeleteBookingCommand command) {
        try {
            reservationRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting reservation: " + e.getMessage());
        }
    }
}