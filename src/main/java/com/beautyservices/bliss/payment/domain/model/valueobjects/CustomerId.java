package com.beautyservices.bliss.payment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CustomerId(Long customerId) {

    public CustomerId() {
        this(0L);
    }

    public CustomerId {
        if (customerId < 0) {
            throw new IllegalArgumentException("CustomerID cannot be negative");
        }
    }
}
