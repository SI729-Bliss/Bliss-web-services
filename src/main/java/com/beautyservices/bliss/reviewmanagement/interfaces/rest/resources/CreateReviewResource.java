
package com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources;

import com.beautyservices.bliss.reviewmanagement.domain.model.valueobjects.ReservationInfo;

public record CreateReviewResource(Long reservationId, int punctuation, String comment) {
}