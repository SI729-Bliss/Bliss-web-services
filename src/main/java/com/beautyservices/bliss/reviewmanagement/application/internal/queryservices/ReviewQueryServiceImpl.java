package com.beautyservices.bliss.reviewmanagement.application.internal.queryservices;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.domain.model.queries.GetReviewsByReservationIdQuery;
import com.beautyservices.bliss.reviewmanagement.domain.services.ReviewQueryService;
import com.beautyservices.bliss.reviewmanagement.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getReviewsByCompanyId(Long companyId) {
        return reviewRepository.findByReservationInfoCompanyId(companyId);
    }

    @Override
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByReservationId(userId);
    }

    @Override
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> getReviewsByReservationId(GetReviewsByReservationIdQuery query) {
        return reviewRepository.findByReservationId(query.reservationId());
    }
}