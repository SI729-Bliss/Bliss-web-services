package com.beautyservices.bliss.profilemanagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Address(String street) {

    public Address {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
    }

    public Address() {
        this(null);
    }
}