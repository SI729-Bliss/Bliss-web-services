package com.beautyservices.bliss.reviewmanagement.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Repository for managing external reservation entities")
public interface ExternalReservationRepository extends JpaRepository<Reservation, Long> {
}