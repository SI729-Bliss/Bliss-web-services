package com.beautyservices.bliss.payment.domain.model.valueobjects;

public enum InvoiceTypes {
    BOLETA(1),
    FACTURA(2);

    private final int value;

    InvoiceTypes(int value) {
        this.value = value;
    }
}
