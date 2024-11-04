package com.beautyservices.bliss.bookingmanagement.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.bookingmanagements.domain.model.aggregates.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingServiceRepository extends JpaRepository<Service, Long> {
}