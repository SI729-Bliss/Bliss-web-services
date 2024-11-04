package com.beautyservices.bliss.bookingmanagement.application.internal.queryservices;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.BookingService;
import com.beautyservices.bliss.bookingmanagement.domain.services.BookingQueryService;
import com.beautyservices.bliss.bookingmanagement.infrastructure.persistence.jpa.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingQueryServiceImpl implements BookingQueryService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Optional<BookingService> findById(Long id) {
        return bookingRepository.findById(id);
    }
}