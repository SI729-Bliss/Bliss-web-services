package com.beautyservices.bliss.payment.domain.model.aggregates;

import com.beautyservices.bliss.payment.domain.model.commands.CreatePaymentCommand;
import com.beautyservices.bliss.payment.domain.model.valueobjects.*;
import com.beautyservices.bliss.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.sun.jdi.InconsistentDebugInfoException;
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
@Table(name = "payments")
public class Payment extends AuditableAbstractAggregateRoot<Payment> {

    @NotNull
    private LocalDate date;

    @NotNull
    private Float amount;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Status status;

    @NotNull
    private InvoiceType invoiceType;

    /*@OneToOne
    @JoinColumn(name = "reservation_id")*/
    @Embedded
    private ReservationId reservationId;

    /*@ManyToOne
    @JoinColumn(name = "customer_id")*/
    @Embedded
    private CustomerId customerId;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;


    public Payment() {
    }

    public Payment(CreatePaymentCommand command) {
        this();
        this.date = LocalDate.now();
        this.amount = command.amount();
        this.paymentMethod = PaymentMethod.YAPE;
        this.status = Status.PENDING;
        this.invoiceType = InvoiceType.BOLETA;
        this.reservationId = new ReservationId(command.reservationId());
        this.customerId = new CustomerId(command.customerId());
    }
}
