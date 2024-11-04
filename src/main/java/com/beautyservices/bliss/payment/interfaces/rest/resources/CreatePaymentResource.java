package com.beautyservices.bliss.payment.interfaces.rest.resources;

public record CreatePaymentResource(Float amount,
                                     Long reservationId,
                                     Long customerId) {
}
