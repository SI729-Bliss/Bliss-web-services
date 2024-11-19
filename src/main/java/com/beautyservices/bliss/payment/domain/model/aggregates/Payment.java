package com.beautyservices.bliss.payment.domain.model.aggregates;

import com.beautyservices.bliss.payment.domain.model.commands.CreatePaymentCommand;
import com.beautyservices.bliss.payment.domain.model.entities.InvoiceType;
import com.beautyservices.bliss.payment.domain.model.entities.PaymentMethod;
import com.beautyservices.bliss.payment.domain.model.entities.Status;
import com.beautyservices.bliss.payment.domain.model.valueobjects.*;
import com.beautyservices.bliss.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.core.Local;
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

    @ManyToOne(fetch = FetchType.EAGER) // Foreign Key
    @JoinColumn(name = "paymentMethod_id", nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER) // Foreign Key
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER) // Foreign Key
    @JoinColumn(name = "invoiceType_id", nullable = false)
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

    public Payment(Float amount, PaymentMethod paymentMethod, Status status, InvoiceType invoiceType, Long reservationId, Long customerId) {
        this();
        this.date = LocalDate.now();
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.invoiceType = invoiceType;
        this.reservationId = new ReservationId(reservationId);
        this.customerId = new CustomerId(customerId);
    }

    public Payment(CreatePaymentCommand command) {
        this();
        this.date = LocalDate.now();
        this.amount = command.amount();
        this.paymentMethod = PaymentMethod.toPaymentMethodFromName(command.paymentMethod());
        this.status =  Status.toStatusFromName(command.status());
        this.invoiceType = InvoiceType.toInvoiceTypeFromName(command.invoiceType());
        this.reservationId = new ReservationId(command.reservationId());
        this.customerId = new CustomerId(command.customerId());
    }
}
