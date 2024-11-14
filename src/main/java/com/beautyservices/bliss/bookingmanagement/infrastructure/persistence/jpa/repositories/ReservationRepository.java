package com.beautyservices.bliss.bookingmanagement.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
