/*package com.beautyservices.bliss.payment.domain.model.aggregates;

import com.beautyservices.bliss.payment.domain.model.commands.CreatePaymentCommand;
import com.beautyservices.bliss.payment.domain.model.valueobjects.ReservationId;
import com.beautyservices.bliss.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends AuditableAbstractAggregateRoot<Payment> {

    @NotNull
    private String status;

    @NotNull
    private String paymentMethod;

    @NotNull
    private Float amount;

    @NotNull
    @JoinColumn(name = "customers_id")
    private Customer customerId;

    @NotNull
    @JoinColumn(name = "reservation_id")
    private ReservationId reservationId;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;


    public Payment() {
    }

    public Payment(CreatePaymentCommand command) {
        this.status = command.status();
        this.paymentMethod = command.paymentMethod();
        this.amount = command.amount();
        this.customerId = new Customer(command.customerId());
    }
}*/
