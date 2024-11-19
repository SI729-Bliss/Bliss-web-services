package com.beautyservices.bliss.reviewmanagement.domain.model.commands;

import java.util.List;

public record UpdateReviewCommand(Long id, int punctuation, String comment, List<String> imageUrls) {}