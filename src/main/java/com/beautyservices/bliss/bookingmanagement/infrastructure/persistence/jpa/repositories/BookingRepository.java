package com.beautyservices.bliss.bookingmanagement.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.BookingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingService, Long> {
    Optional<BookingService> findByCustomerId(Long customerId);
    List<BookingService> findByServiceId(Long serviceId);
    List<BookingService> findByDate(String date);
}