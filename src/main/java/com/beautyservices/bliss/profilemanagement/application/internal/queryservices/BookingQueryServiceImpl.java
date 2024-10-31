package com.beautyservices.bliss.profilemanagement.application.internal.queryservices;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.BookingService;
import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetBookingByIdQuery;
import com.beautyservices.bliss.profilemanagement.domain.services.BookingQueryService;
import com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingQueryServiceImpl implements BookingQueryService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Optional<BookingService> handle(GetBookingByIdQuery query) {
        return bookingRepository.findById(query.bookingId());
    }

    @Override
    public Object findById(Long id) {
        return null;
    }
}