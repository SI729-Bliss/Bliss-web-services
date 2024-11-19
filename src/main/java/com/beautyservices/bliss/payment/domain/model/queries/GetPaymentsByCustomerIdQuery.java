package com.beautyservices.bliss.payment.domain.model.queries;

import com.beautyservices.bliss.payment.domain.model.valueobjects.CustomerId;

public record GetPaymentsByCustomerIdQuery(CustomerId customerId) {
}
