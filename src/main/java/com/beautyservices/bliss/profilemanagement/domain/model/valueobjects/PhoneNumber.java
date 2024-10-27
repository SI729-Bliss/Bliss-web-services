package com.beautyservices.bliss.profilemanagement.domain.model.valueobjects;


import jakarta.persistence.Embeddable;

@Embeddable
public record PhoneNumber(String value) {

    public PhoneNumber {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be null or blank");
        }
        if (!value.matches("\\+?[0-9. ()-]{7,25}")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }

    public PhoneNumber() {
        this(null);
    }
}
