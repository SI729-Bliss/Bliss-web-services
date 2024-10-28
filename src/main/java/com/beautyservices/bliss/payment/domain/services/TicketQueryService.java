package com.beautyservices.bliss.payment.domain.services;

import com.beautyservices.bliss.payment.domain.model.aggregates.Ticket;
import com.beautyservices.bliss.payment.domain.model.queries.GetTicketByIdQuery;

import java.util.Optional;

public interface TicketQueryService {
    Optional<Ticket> handle(GetTicketByIdQuery query);
}
