package com.beautyservices.bliss.payment.domain.services;

import com.beautyservices.bliss.payment.domain.model.commands.SeedPaymentMethodsCommand;

public interface PaymentMethodCommandService {
    void handle(SeedPaymentMethodsCommand command);
}
