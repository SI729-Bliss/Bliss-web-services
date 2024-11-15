// src/main/java/com/beautyservices/bliss/reviewmanagement/domain/services/ReviewQueryService.java
package com.beautyservices.bliss.reviewmanagement.domain.services;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.domain.model.queries.GetReviewByReservationIdQuery;

import java.util.Optional;

public interface ReviewQueryService {
    Optional<Review> handle(GetReviewByReservationIdQuery query);
}