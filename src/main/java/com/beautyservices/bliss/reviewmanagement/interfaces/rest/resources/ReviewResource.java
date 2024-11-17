package com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources;

import com.beautyservices.bliss.reviewmanagement.domain.model.valueobjects.ReservationInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Resource representing a review")
public record ReviewResource(
        @Schema(description = "ID of the review", example = "1") Long id,
        @Schema(description = "ID of the reservation", example = "1") Long reservationId,
        @Schema(description = "Punctuation of the review", example = "5") int punctuation,
        @Schema(description = "Comment of the review", example = "Great service!") String comment,
        @Schema(description = "Information about the reservation") ReservationInfo reservationInfo,
        @Schema(description = "List of image URLs", example = "[\"http://example.com/image1.jpg\", \"http://example.com/image2.jpg\"]") List<String> imageUrls) {
}