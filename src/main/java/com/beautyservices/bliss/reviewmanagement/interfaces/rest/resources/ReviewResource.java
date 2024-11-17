package com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources;

import com.beautyservices.bliss.reviewmanagement.domain.model.valueobjects.ReservationInfo;

public record ReviewResource(Long id, Long reservationId, int punctuation, String comment, ReservationInfo reservationInfo) {
}