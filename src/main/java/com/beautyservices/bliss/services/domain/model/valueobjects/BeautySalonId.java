package com.beautyservices.bliss.services.domain.model.valueobjects;

public record BeautySalonId(Long beautySalonId) {
    public BeautySalonId {
        if (beautySalonId <= 0) {
            throw new IllegalArgumentException("Salon beautySalonId cannot be negative or 0");
        }
    }

    public BeautySalonId() {
        this(0L);
    }
}
