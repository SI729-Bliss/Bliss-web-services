package com.beautyservices.bliss.payment.interfaces.rest.transform;

import com.beautyservices.bliss.payment.domain.model.commands.CreatePaymentCommand;
import com.beautyservices.bliss.payment.domain.model.valueobjects.CustomerId;
import com.beautyservices.bliss.payment.interfaces.rest.resources.CreatePaymentResource;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource) {
        return new CreatePaymentCommand(resource.amount(), resource.reservationId(), resource.customerId());
    }
}