package com.beautyservices.bliss.reviewmanagement.domain.model.commands;

import java.util.List;

public record CreateReviewCommand(Long reservationId, Long userId, int punctuation, String comment, List<String> imageUrls) {}