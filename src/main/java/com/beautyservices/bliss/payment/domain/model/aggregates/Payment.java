package com.beautyservices.bliss.payment.domain.model.aggregates;

import com.beautyservices.bliss.payment.domain.model.commands.CreatePaymentCommand;
import com.beautyservices.bliss.payment.domain.model.valueobjects.CustomerId;
import com.beautyservices.bliss.payment.domain.model.valueobjects.PaymentMethod;
import com.beautyservices.bliss.payment.domain.model.valueobjects.ReservationId;
import com.beautyservices.bliss.payment.domain.model.valueobjects.Status;
import com.beautyservices.bliss.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends AuditableAbstractAggregateRoot<Payment> {

    @NotNull
    private LocalDate date;

    @NotNull
    private Float amount;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Status status;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @NotNull
    @JoinColumn(name = "customers_id")
    private CustomerId customerId;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;


    public Payment() {
    }

    public Payment(CreatePaymentCommand command, Ticket ticket) {
        this();
        this.date = LocalDate.now();
        this.amount = command.amount();
        this.paymentMethod = PaymentMethod.YAPE;
        this.status = Status.PENDING;
        this.ticket = ticket;
        this.customerId = new CustomerId(command.customerId());
    }
}
