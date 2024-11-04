package com.beautyservices.bliss.payment.domain.model.commands;

import com.beautyservices.bliss.payment.domain.model.valueobjects.InvoiceType;
import com.beautyservices.bliss.payment.domain.model.valueobjects.PaymentMethod;
import com.beautyservices.bliss.payment.domain.model.valueobjects.Status;

public record CreatePaymentCommand(Float amount,
                                   Long reservationId,
                                   Long customerId) {
}