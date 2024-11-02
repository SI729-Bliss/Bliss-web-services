package com.beautyservices.bliss.payment.interfaces.rest.transform;

import com.beautyservices.bliss.payment.domain.model.aggregates.Ticket;
import com.beautyservices.bliss.payment.interfaces.rest.resources.TicketResource;

public class TicketResourceFromEntityAssembler {
    public static TicketResource toResourceFromEntity(Ticket ticket) {
        return new TicketResource(ticket.getId(), ticket.getDate(),
                ticket.getReservationId().reservationId());
    }
}
