package com.beautyservices.bliss.payment.application.internal.commandservices;

import com.beautyservices.bliss.payment.domain.model.aggregates.Ticket;
import com.beautyservices.bliss.payment.domain.model.commands.CreateTicketCommand;
import com.beautyservices.bliss.payment.domain.model.commands.DeleteTicketCommand;
import com.beautyservices.bliss.payment.domain.services.TicketCommandService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketCommandServiceImpl implements TicketCommandService {

    private final TicketRepository ticketRepository;

    public TicketCommandServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Long handle(CreateTicketCommand command) {
        var ticket = new Ticket(command);
        try{
            ticketRepository.save(ticket);
        } catch(Exception e){
            throw new IllegalArgumentException("Error while saving ticket." + e.getMessage());
        }
        return ticket.getId();
    }

    @Override
    public void handle(DeleteTicketCommand command) {
        if(!ticketRepository.existsById(command.id())){
            throw new IllegalArgumentException("Ticket with id " + command.id() + " does not exist.");

        }
        ticketRepository.deleteById(command.id());
    }
}
