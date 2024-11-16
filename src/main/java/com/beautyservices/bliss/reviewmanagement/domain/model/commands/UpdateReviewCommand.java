package com.beautyservices.bliss.reviewmanagement.domain.model.commands;

public record UpdateReviewCommand(Long id, int punctuation, String comment) {
}