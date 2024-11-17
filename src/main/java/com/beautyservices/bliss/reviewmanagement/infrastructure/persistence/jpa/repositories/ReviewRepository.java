// ReviewRepository.java
package com.beautyservices.bliss.reviewmanagement.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByReservationInfoCompanyId(Long companyId);
    List<Review> findByReservation_Id(Long reservationId);
}