package com.beautyservices.bliss.payment.application.internal.queryservices;

import com.beautyservices.bliss.payment.domain.model.aggregates.Payment;
import com.beautyservices.bliss.payment.domain.model.queries.GetAllPaymentsQuery;
import com.beautyservices.bliss.payment.domain.model.queries.GetPaymentByIdQuery;
import com.beautyservices.bliss.payment.domain.model.queries.GetPaymentsByCustomerIdQuery;
import com.beautyservices.bliss.payment.domain.services.PaymentQueryService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {

    private final PaymentRepository paymentRepository;

    public PaymentQueryServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> handle(GetPaymentByIdQuery query) {
        return paymentRepository.findById(query.paymentId());
    }

    @Override
    public List<Payment> handle(GetPaymentsByCustomerIdQuery query) {
        return paymentRepository.findByCustomerId(query.customerId());
    }

    @Override
    public List<Payment> handle(GetAllPaymentsQuery query) {
        return paymentRepository.findAll();
    }
}
