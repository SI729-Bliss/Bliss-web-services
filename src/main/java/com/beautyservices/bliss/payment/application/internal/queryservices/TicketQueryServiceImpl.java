package com.beautyservices.bliss.payment.application.internal.queryservices;

import com.beautyservices.bliss.payment.domain.model.aggregates.Ticket;
import com.beautyservices.bliss.payment.domain.model.queries.GetTicketByIdQuery;
import com.beautyservices.bliss.payment.domain.services.TicketQueryService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketQueryServiceImpl implements TicketQueryService {

    private final TicketRepository ticketRepository;

    public TicketQueryServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Optional<Ticket> handle(GetTicketByIdQuery query) {
        return this.ticketRepository.findById(query.id());
    }
}
