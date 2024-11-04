package com.beautyservices.bliss.payment.application.internal.commandservices;

import com.beautyservices.bliss.payment.domain.model.aggregates.Payment;
import com.beautyservices.bliss.payment.domain.model.commands.CreatePaymentCommand;
import com.beautyservices.bliss.payment.domain.services.PaymentCommandService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final PaymentRepository paymentRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Long handle(CreatePaymentCommand command) {
        Payment payment = new Payment(command);
        try {
            paymentRepository.save(payment);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while saving payment: " + e.getMessage());
        }
        return payment.getId();
    }
}
