package com.beautyservices.bliss.reviewmanagement.domain.services;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.CreateReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.DeleteReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.UpdateReviewCommand;

import java.util.Optional;

public interface ReviewCommandService {
    Review handle(CreateReviewCommand command);
    Optional<Review> handle(UpdateReviewCommand command);
    void handle(DeleteReviewCommand command);
}