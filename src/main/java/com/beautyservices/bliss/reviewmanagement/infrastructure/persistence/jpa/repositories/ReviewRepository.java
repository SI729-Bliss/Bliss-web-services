package com.beautyservices.bliss.reviewmanagement.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Repository;

import java.util.List;

@Schema(description = "Repository for managing review entities")
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long userId);
    List<Review> findByReservationId(Long reservationId);
    List<Review> findByReservationInfoCompanyId(Long companyId);
}