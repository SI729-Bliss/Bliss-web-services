package com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Resource for creating a review")
public record CreateReviewResource(
        @Schema(description = "ID of the reservation", example = "1") Long reservationId,
        @Schema(description = "ID of the user", example = "1") Long userId,
        @Schema(description = "Punctuation of the review", example = "5") int punctuation,
        @Schema(description = "Comment of the review", example = "Great service!") String comment,
        @Schema(description = "List of image URLs", example = "[\"http://example.com/image1.jpg\", \"http://example.com/image2.jpg\"]") List<String> imageUrls) {
}