package com.beautyservices.bliss.reviewmanagement.domain.services;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.CreateReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.UpdateReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.DeleteReviewCommand;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Optional;

@Schema(description = "Service for handling review commands")
public interface ReviewCommandService {
    Optional<Review> createReview(CreateReviewCommand command);
    Optional<Review> updateReview(UpdateReviewCommand command);
    void deleteReview(DeleteReviewCommand command);
}