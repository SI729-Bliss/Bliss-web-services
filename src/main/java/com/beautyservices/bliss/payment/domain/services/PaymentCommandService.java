package com.beautyservices.bliss.payment.domain.services;

import com.beautyservices.bliss.payment.domain.model.commands.CreatePaymentCommand;

public interface PaymentCommandService {
    Long handle(CreatePaymentCommand command);
}