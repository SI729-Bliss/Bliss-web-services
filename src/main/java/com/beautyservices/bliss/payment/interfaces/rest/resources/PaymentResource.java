package com.beautyservices.bliss.payment.interfaces.rest.resources;

import com.beautyservices.bliss.payment.domain.model.valueobjects.InvoiceTypes;
import com.beautyservices.bliss.payment.domain.model.valueobjects.PaymentMethods;
import com.beautyservices.bliss.payment.domain.model.valueobjects.Statuses;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record PaymentResource(Long paymentId,
                              LocalDate date,
                              Float amount,
                              String paymentMethod,
                              String status,
                              String invoiceType,
                              Long reservationId,
                              Long customerId) {
}
