package com.beautyservices.bliss.payment.domain.services;

import com.beautyservices.bliss.payment.domain.model.commands.CreateTicketCommand;
import com.beautyservices.bliss.payment.domain.model.commands.DeleteTicketCommand;

public interface TicketCommandService {
    Long handle(CreateTicketCommand command);
    void handle(DeleteTicketCommand command);
}
