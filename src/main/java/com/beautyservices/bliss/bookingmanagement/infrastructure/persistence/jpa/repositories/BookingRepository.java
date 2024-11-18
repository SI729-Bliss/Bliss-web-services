package com.beautyservices.bliss.bookingmanagement.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomerId(Long customerId);
    List<Reservation> findByService_Id(Long serviceId);
    List<Reservation> findByCompany_Id(Long companyId);
}