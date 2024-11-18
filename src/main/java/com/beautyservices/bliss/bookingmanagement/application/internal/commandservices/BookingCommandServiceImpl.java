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
    public Long handle(UpdateBookingCommand command) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(command.id());
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            reservation.update(command);
            try {
                reservationRepository.save(reservation);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error while updating reservation: " + e.getMessage());
            }
            return Optional.of(reservation);
        }
        return Optional.empty();
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