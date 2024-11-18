package com.beautyservices.bliss.payment.domain.model.valueobjects;

public enum Statuses {
    PENDING(1),
    PAID(2),
    CANCELLED(3);

    private final int value;

    Statuses(int value) {
        this.value = value;
    }
}
