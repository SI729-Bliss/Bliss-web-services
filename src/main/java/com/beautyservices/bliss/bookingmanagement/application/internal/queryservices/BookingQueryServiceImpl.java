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

    @Autowired
    private BookingRepository reservationRepository;

    @Override
    public Optional<Reservation> handle(GetBookingByIdQuery query) {
        return reservationRepository.findById(query.bookingId());
    }

    @Override
    public List<Reservation> handle(GetBookingsByCustomerIdQuery query) {
        return reservationRepository.findByCustomerId(query.customerId());
    }

    @Override
    public List<Reservation> handle(GetBookingsByServiceIdQuery query) {
        return reservationRepository.findByService_Id(query.serviceId());
    }



    @Override
    public List<Reservation> handle(GetAllBookingsQuery query) {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> handle(GetBookingsByCompanyIdQuery query) {
        return reservationRepository.findByCompany_Id(query.companyId());
    }
}