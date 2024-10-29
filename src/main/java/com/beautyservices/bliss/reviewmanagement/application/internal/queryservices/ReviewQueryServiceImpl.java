package com.beautyservices.bliss.reviewmanagement.application.internal.queryservices;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.domain.model.queries.GetReviewByReservationIdQuery;
import com.beautyservices.bliss.reviewmanagement.domain.services.ReviewQueryService;
import com.beautyservices.bliss.reviewmanagement.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Optional<Review> handle(GetReviewByReservationIdQuery query) {
        return reviewRepository.findByReservationId(query.reservationId());
    }
}