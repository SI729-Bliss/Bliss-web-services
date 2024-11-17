package com.beautyservices.bliss.reviewmanagement.domain.services;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.domain.model.queries.GetReviewsByReservationIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    List<Review> getReviewsByCompanyId(Long companyId);
    List<Review> getReviewsByUserId(Long userId);
    Optional<Review> getReviewById(Long id);
    List<Review> getReviewsByReservationId(GetReviewsByReservationIdQuery query);

}