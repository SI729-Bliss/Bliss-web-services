package com.beautyservices.bliss.bookingmanagement.application.internal.queryservices;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import com.beautyservices.bliss.bookingmanagement.domain.model.queries.*;
import com.beautyservices.bliss.bookingmanagement.domain.services.BookingQueryService;
import com.beautyservices.bliss.bookingmanagement.infrastructure.persistence.jpa.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingQueryServiceImpl implements BookingQueryService {


    private final BookingRepository reservationRepository;

    public BookingQueryServiceImpl(BookingRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Optional<Reservation> handle(GetReservationByIdQuery query) {
        return this.reservationRepository.findById(query.bookingId());
    }

    @Override
    public List<Reservation> handle(GetReservationsByCustomerIdQuery query) {
        return this.reservationRepository.findByCustomerId(query.customerId());
    }

    @Override
    public List<Reservation> handle(GetReservationsByServiceIdQuery query) {
        return this.reservationRepository.findByService_Id(query.serviceId());
    }



    @Override
    public List<Reservation> handle(GetAllReservationsQuery query) {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> handle(GetReservationsByCompanyIdQuery query) {
        return reservationRepository.findByCompany_Id(query.companyId());
    }
}