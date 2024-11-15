package com.beautyservices.bliss.payment.domain.model.entities;

import com.beautyservices.bliss.payment.domain.model.valueobjects.PaymentMethods;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(name = "payment_methods")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private PaymentMethods name;

    public PaymentMethod(PaymentMethods name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static PaymentMethod getDefaultPaymentMethod() {
        return new PaymentMethod(PaymentMethods.CASH);
    }

    public static PaymentMethod toPaymentMethodFromName(String name) {
        return new PaymentMethod(PaymentMethods.valueOf(name));
    }
}
