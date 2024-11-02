package com.beautyservices.bliss.payment.interfaces.rest.resources;

public record CreatePaymentResource(Float amount,
                                     Long ticket,
                                     Long customerId) {
}
