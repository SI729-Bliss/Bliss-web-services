package com.beautyservices.bliss.reviewmanagement.domain.services;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.domain.model.queries.GetReviewByIdQuery;
import com.beautyservices.bliss.reviewmanagement.domain.model.queries.GetReviewsByCompanyIdQuery;
import com.beautyservices.bliss.reviewmanagement.domain.model.queries.GetReviewsByReservationIdQuery;
import com.beautyservices.bliss.reviewmanagement.domain.model.queries.GetReviewsByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    List<Review> handle (GetReviewsByCompanyIdQuery query);
    List<Review> handle (GetReviewsByUserIdQuery query);
    Optional<Review> handle (GetReviewByIdQuery query);
    List<Review> handle (GetReviewsByReservationIdQuery query);

}