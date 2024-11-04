package com.beautyservices.bliss.reviewmanagement.application.internal.commandservices;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.CreateReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.DeleteReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.UpdateReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.services.ReviewCommandService;
import com.beautyservices.bliss.reviewmanagement.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review handle(CreateReviewCommand command) {
        Review review = new Review();
        review.setPunctuation(command.punctuation());
        review.setComment(command.comment());
        review.setReservationId(command.reservationId());
        review.setImages(command.images());
        return reviewRepository.save(review);
    }

    @Override
    public Optional<Review> handle(UpdateReviewCommand command) {
        var reviewId = command.id();

        if (!reviewRepository.existsById(reviewId)) {
            throw new IllegalArgumentException("Review with id " + reviewId + " does not exist");
        }

        var reviewToUpdate = reviewRepository.findById(reviewId).get();
        reviewToUpdate.setPunctuation(command.punctuation());
        reviewToUpdate.setComment(command.comment());
        reviewToUpdate.setImages(command.images());

        try {
            var updatedReview = reviewRepository.save(reviewToUpdate);
            return Optional.of(updatedReview);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating review: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteReviewCommand command) {
        var reviewId = command.id();

        if (!reviewRepository.existsById(reviewId)) {
            throw new IllegalArgumentException("Review with id " + reviewId + " does not exist");
        }

        reviewRepository.deleteById(reviewId);
    }
}