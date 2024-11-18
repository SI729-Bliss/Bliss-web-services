package com.beautyservices.bliss.payment.domain.model.queries;

import com.beautyservices.bliss.payment.domain.model.valueobjects.ReservationId;

public record GetPaymentByReservationIdQuery(ReservationId reservationId) {
}
