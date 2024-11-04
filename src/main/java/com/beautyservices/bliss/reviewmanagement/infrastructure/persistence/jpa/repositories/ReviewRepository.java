package com.beautyservices.bliss.reviewmanagement.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReservationId(Long reservationId);
}