// ExternalReservationRepository.java
package com.beautyservices.bliss.reviewmanagement.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalReservationRepository extends JpaRepository<Reservation, Long> {
}