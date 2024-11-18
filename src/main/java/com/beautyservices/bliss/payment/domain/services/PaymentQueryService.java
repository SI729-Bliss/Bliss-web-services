package com.beautyservices.bliss.payment.domain.services;

import com.beautyservices.bliss.payment.domain.model.aggregates.Payment;
import com.beautyservices.bliss.payment.domain.model.queries.GetAllPaymentsQuery;
import com.beautyservices.bliss.payment.domain.model.queries.GetPaymentByIdQuery;
import com.beautyservices.bliss.payment.domain.model.queries.GetPaymentByReservationIdQuery;
import com.beautyservices.bliss.payment.domain.model.queries.GetPaymentsByCustomerIdQuery;

import java.util.List;
import java.util.Optional;

public interface PaymentQueryService {
    Optional<Payment> handle(GetPaymentByIdQuery query);
    List<Payment> handle(GetPaymentsByCustomerIdQuery query);
    List<Payment> handle(GetAllPaymentsQuery query);
    Optional<Payment> handle(GetPaymentByReservationIdQuery query);
}
