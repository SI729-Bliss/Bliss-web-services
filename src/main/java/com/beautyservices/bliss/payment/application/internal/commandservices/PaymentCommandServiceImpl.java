package com.beautyservices.bliss.payment.application.internal.commandservices;

import com.beautyservices.bliss.payment.domain.model.aggregates.Payment;
import com.beautyservices.bliss.payment.domain.model.commands.CreatePaymentCommand;
import com.beautyservices.bliss.payment.domain.model.valueobjects.InvoiceTypes;
import com.beautyservices.bliss.payment.domain.model.valueobjects.PaymentMethods;
import com.beautyservices.bliss.payment.domain.model.valueobjects.Statuses;
import com.beautyservices.bliss.payment.domain.services.PaymentCommandService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.InvoiceTypeRepository;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.PaymentMethodRepository;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.PaymentRepository;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.StatusRepository;
import org.springframework.stereotype.Service;


@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final PaymentRepository paymentRepository;
    private final InvoiceTypeRepository invoiceTypeRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final StatusRepository statusRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository, InvoiceTypeRepository invoiceTypeRepository, PaymentMethodRepository paymentMethodRepository, StatusRepository statusRepository) {
        this.paymentRepository = paymentRepository;
        this.invoiceTypeRepository = invoiceTypeRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public Long handle(CreatePaymentCommand command) {

        var paymentMethod = this.paymentMethodRepository.findByName(PaymentMethods.valueOf(command.paymentMethod()))
                .orElseThrow(() -> new IllegalArgumentException("PaymentMethod with name " + command.paymentMethod() + " not found"));

        var status = this.statusRepository.findByName(Statuses.valueOf(command.status()))
                .orElseThrow(() -> new IllegalArgumentException("Status with name " + command.status() + " not found"));

        var invoiceType = this.invoiceTypeRepository.findByName(InvoiceTypes.valueOf(command.invoiceType()))
                .orElseThrow(() -> new IllegalArgumentException("InvoiceType with name " + command.invoiceType() + " not found"));


        var payment = new Payment(command.amount(), paymentMethod, status,invoiceType, command.reservationId(), command.customerId());

        try {
            this.paymentRepository.save(payment);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while saving payment: " + e.getMessage());
        }
        return payment.getId();
    }
}
