package com.beautyservices.bliss.payment.interfaces.rest.resources;

import com.beautyservices.bliss.payment.domain.model.valueobjects.InvoiceTypes;
import com.beautyservices.bliss.payment.domain.model.valueobjects.PaymentMethods;
import com.beautyservices.bliss.payment.domain.model.valueobjects.Statuses;

public record CreatePaymentResource(Float amount,
                                    String paymentMethod,
                                    String status,
                                    String invoiceType,
                                    Long reservationId,
                                    Long customerId) {
}
