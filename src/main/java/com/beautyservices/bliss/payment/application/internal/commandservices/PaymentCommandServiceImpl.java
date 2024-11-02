package com.beautyservices.bliss.payment.application.internal.commandservices;

import com.beautyservices.bliss.payment.domain.model.aggregates.Payment;
import com.beautyservices.bliss.payment.domain.model.aggregates.Ticket;
import com.beautyservices.bliss.payment.domain.model.commands.CreatePaymentCommand;
import com.beautyservices.bliss.payment.domain.services.PaymentCommandService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.PaymentRepository;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.TicketRepository;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final PaymentRepository paymentRepository;
    private final TicketRepository ticketRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository, TicketRepository ticketRepository) {
        this.paymentRepository = paymentRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Long handle(CreatePaymentCommand command) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(command.ticketId());
        if(optionalTicket.isEmpty())
            throw new IllegalArgumentException("Ticket does not exist");
        Ticket ticket = optionalTicket.get();
        Payment payment = new Payment(command, ticket);
        try {
            paymentRepository.save(payment);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while saving payment: " + e.getMessage());
        }
        return payment.getId();
    }
}
