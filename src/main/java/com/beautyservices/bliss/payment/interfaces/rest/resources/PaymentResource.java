package com.beautyservices.bliss.payment.interfaces.rest.resources;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record PaymentResource(Long paymentId,
                              LocalDate date,
                              Float amount,
                              String paymentMethod,
                              String status,
                              Long ticketId,
                              Long customerId) {
}
