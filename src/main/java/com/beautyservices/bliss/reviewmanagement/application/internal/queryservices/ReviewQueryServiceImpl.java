package com.beautyservices.bliss.reviewmanagement.application.internal.queryservices;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.domain.model.queries.GetReviewByIdQuery;
import com.beautyservices.bliss.reviewmanagement.domain.model.queries.GetReviewsByCompanyIdQuery;
import com.beautyservices.bliss.reviewmanagement.domain.model.queries.GetReviewsByReservationIdQuery;
import com.beautyservices.bliss.reviewmanagement.domain.model.queries.GetReviewsByUserIdQuery;
import com.beautyservices.bliss.reviewmanagement.domain.services.ReviewQueryService;
import com.beautyservices.bliss.reviewmanagement.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> handle(GetReviewsByCompanyIdQuery query) {
        return reviewRepository.findByReservationInfoCompanyId(query.companyId());
    }

    @Override
    public List<Review> handle(GetReviewsByUserIdQuery query) {
        return reviewRepository.findByUserId(query.userId());
    }

    @Override
    public Optional<Review> handle(GetReviewByIdQuery query) {
        return reviewRepository.findById(query.id());
    }

    @Override
    public List<Review> handle(GetReviewsByReservationIdQuery query) {
        return reviewRepository.findByReservationId(query.reservationId());
    }
}