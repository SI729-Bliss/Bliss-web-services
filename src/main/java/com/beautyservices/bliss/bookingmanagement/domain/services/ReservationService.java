package com.beautyservices.bliss.bookingmanagement.domain.services;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import com.beautyservices.bliss.bookingmanagement.infrastructure.persistence.jpa.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
