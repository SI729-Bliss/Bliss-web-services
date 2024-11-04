package com.beautyservices.bliss.payment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ReservationId(Long reservationId) {

    public ReservationId() {
        this(0L);
    }

    public ReservationId {
        if (reservationId < 0) {
            throw new IllegalArgumentException("ReservationId cannot be negative");
        }
    }

}
