package com.beautyservices.bliss.payment.domain.model.aggregates;

import com.beautyservices.bliss.payment.domain.model.commands.CreateTicketCommand;
import com.beautyservices.bliss.payment.domain.model.valueobjects.ReservationId;
import com.beautyservices.bliss.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Entity
public class Ticket extends AuditableAbstractAggregateRoot<Ticket> {

    private LocalDate date;

    @Getter
    @Embedded
    private ReservationId reservationId;

    public Ticket() {
    }

    public Ticket(CreateTicketCommand command) {
        this();
        this.date = LocalDate.now();
        this.reservationId = new ReservationId(command.reservationId());
    }

}
