package com.beautyservices.bliss.reviewmanagement.domain.model.commands;

import com.beautyservices.bliss.reviewmanagement.domain.model.valueobjects.Punctuation;

public record CreateReviewCommand(Punctuation punctuation, String comment, Long reservationId, String images) {
}