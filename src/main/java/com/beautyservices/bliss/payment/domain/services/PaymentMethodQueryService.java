package com.beautyservices.bliss.payment.domain.services;

import com.beautyservices.bliss.payment.domain.model.commands.SeedPaymentMethodsCommand;
import com.beautyservices.bliss.payment.domain.model.entities.PaymentMethod;
import com.beautyservices.bliss.payment.domain.model.queries.GetPaymentMethodByNameQuery;

import java.util.Optional;

public interface PaymentMethodQueryService {
    Optional<PaymentMethod> handle(GetPaymentMethodByNameQuery query);
}
