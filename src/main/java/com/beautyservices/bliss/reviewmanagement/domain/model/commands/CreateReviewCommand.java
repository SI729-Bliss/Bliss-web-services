
package com.beautyservices.bliss.reviewmanagement.domain.model.commands;

public record CreateReviewCommand(Long reservationId, int punctuation, String comment) {}