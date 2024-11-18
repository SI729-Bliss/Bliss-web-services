package com.beautyservices.bliss.payment.domain.model.queries;

import com.beautyservices.bliss.payment.domain.model.valueobjects.PaymentMethods;

public record GetPaymentMethodByNameQuery(PaymentMethods name) {
}
