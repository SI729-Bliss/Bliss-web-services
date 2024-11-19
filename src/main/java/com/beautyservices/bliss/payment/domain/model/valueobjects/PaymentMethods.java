package com.beautyservices.bliss.payment.domain.model.valueobjects;

public enum PaymentMethods {
    CASH(1),
    YAPE(2),
    PLIN(3),
    VISA(4);

    private final int value;

    PaymentMethods(int value) {
        this.value = value;
    }
}
