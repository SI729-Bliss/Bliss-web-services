package com.beautyservices.bliss.profilemanagement.domain.model.valueobjects;


import jakarta.persistence.Embeddable;

@Embeddable
public record PhoneNumber(String number) {

    public PhoneNumber {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be null or blank");
        }

    }

    public PhoneNumber() {
        this(null);
    }
}