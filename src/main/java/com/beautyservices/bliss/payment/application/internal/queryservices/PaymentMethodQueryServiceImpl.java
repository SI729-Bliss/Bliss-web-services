package com.beautyservices.bliss.payment.application.internal.queryservices;

import com.beautyservices.bliss.payment.domain.model.entities.PaymentMethod;
import com.beautyservices.bliss.payment.domain.model.queries.GetPaymentMethodByNameQuery;
import com.beautyservices.bliss.payment.domain.services.PaymentMethodQueryService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentMethodQueryServiceImpl implements PaymentMethodQueryService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodQueryServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public Optional<PaymentMethod> handle(GetPaymentMethodByNameQuery query) {
        return this.paymentMethodRepository.findByName(query.name());
    }
}
