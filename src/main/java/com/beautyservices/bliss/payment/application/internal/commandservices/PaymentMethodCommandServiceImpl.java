package com.beautyservices.bliss.payment.application.internal.commandservices;

import com.beautyservices.bliss.payment.domain.model.commands.SeedPaymentMethodsCommand;
import com.beautyservices.bliss.payment.domain.model.entities.InvoiceType;
import com.beautyservices.bliss.payment.domain.model.entities.PaymentMethod;
import com.beautyservices.bliss.payment.domain.model.valueobjects.InvoiceTypes;
import com.beautyservices.bliss.payment.domain.model.valueobjects.PaymentMethods;
import com.beautyservices.bliss.payment.domain.services.PaymentMethodCommandService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PaymentMethodCommandServiceImpl implements PaymentMethodCommandService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodCommandServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public void handle(SeedPaymentMethodsCommand command) {
        Arrays.stream(PaymentMethods.values())
                .forEach(paymentMethod -> {
                    if(!paymentMethodRepository.existsByName(paymentMethod)) {
                        paymentMethodRepository.save(new PaymentMethod(PaymentMethods.valueOf(paymentMethod.name())));
                    }
                } );
    }
}