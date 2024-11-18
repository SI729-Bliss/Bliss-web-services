package com.beautyservices.bliss.reviewmanagement.application.internal.commandservices;

import com.beautyservices.bliss.reviewmanagement.domain.model.commands.CreateReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.UpdateReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.DeleteReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.domain.model.valueobjects.ReservationInfo;
import com.beautyservices.bliss.reviewmanagement.domain.services.ReviewCommandService;
import com.beautyservices.bliss.reviewmanagement.infrastructure.persistence.jpa.repositories.ExternalReservationRepository;
import com.beautyservices.bliss.reviewmanagement.infrastructure.persistence.jpa.repositories.ReviewRepository;
import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ExternalReservationRepository externalReservationRepository;

    @Override
    public Optional<Review> createReview(CreateReviewCommand command) {
        Optional<Reservation> reservationOpt = externalReservationRepository.findById(command.reservationId());
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            ReservationInfo reservationInfo = new ReservationInfo(reservation.getServiceId(), reservation.getCompanyId());
            Review review = new Review(reservation.getId(), command.punctuation(), command.comment(), reservationInfo, command.imageUrls());            try {
                reviewRepository.save(review);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error while saving review: " + e.getMessage());
            }
            return Optional.of(review);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Review> updateReview(UpdateReviewCommand command) {
        Optional<Review> reviewOpt = reviewRepository.findById(command.id());
        if (reviewOpt.isPresent()) {
            Review review = reviewOpt.get();
            review.setPunctuation(command.punctuation());
            review.setComment(command.comment());
            review.setImageUrls(command.imageUrls());
            reviewRepository.save(review);
            return Optional.of(review);
        }
        return Optional.empty();
    }

    @Override
    public void deleteReview(DeleteReviewCommand command) {
        reviewRepository.deleteById(command.id());
    }
}