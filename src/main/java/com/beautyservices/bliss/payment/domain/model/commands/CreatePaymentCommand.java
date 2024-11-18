package com.beautyservices.bliss.payment.domain.model.commands;


public record CreatePaymentCommand(Float amount,
                                   String paymentMethod,
                                   String status,
                                   String invoiceType,
                                   Long reservationId,
                                   Long customerId) {
}