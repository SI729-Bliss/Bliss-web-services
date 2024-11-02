package com.beautyservices.bliss.payment.domain.model.commands;

import com.beautyservices.bliss.payment.domain.model.valueobjects.PaymentMethod;
import com.beautyservices.bliss.payment.domain.model.valueobjects.Status;

public record CreatePaymentCommand(Float amount,
                                   Long ticketId,
                                   Long customerId) {
}