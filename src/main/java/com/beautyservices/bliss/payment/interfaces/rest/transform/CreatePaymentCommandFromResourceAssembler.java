package com.beautyservices.bliss.payment.interfaces.rest.transform;

import com.beautyservices.bliss.payment.domain.model.commands.CreatePaymentCommand;
import com.beautyservices.bliss.payment.interfaces.rest.resources.CreatePaymentResource;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource) {
        return new CreatePaymentCommand(
                resource.amount(),
                resource.paymentMethod(),
                resource.status(),
                resource.invoiceType(),
                resource.reservationId(),
                resource.customerId());
    }
}
