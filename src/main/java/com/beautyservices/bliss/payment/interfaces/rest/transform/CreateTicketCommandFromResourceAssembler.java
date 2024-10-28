package com.beautyservices.bliss.payment.interfaces.rest.transform;

import com.beautyservices.bliss.payment.domain.model.commands.CreateTicketCommand;
import com.beautyservices.bliss.payment.interfaces.rest.resources.CreateTicketResource;

public class CreateTicketCommandFromResourceAssembler {
    public static CreateTicketCommand toCommandFromResource(Long reservationId) {
        return new CreateTicketCommand(reservationId);
    }
}
