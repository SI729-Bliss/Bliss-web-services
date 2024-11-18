package com.beautyservices.bliss.payment.interfaces.rest.transform;

import com.beautyservices.bliss.payment.domain.model.aggregates.Payment;
import com.beautyservices.bliss.payment.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {
    public static PaymentResource toResourceFromEntity(Payment payment) {
        return new PaymentResource(
                payment.getId(),
                payment.getDate(),
                payment.getAmount(),
                payment.getPaymentMethod().getStringName(),
                payment.getStatus().getStringName(),
                payment.getInvoiceType().getStringName(),
                payment.getReservationId().reservationId(),
                payment.getCustomerId().customerId());
    }
}
