package com.beautyservices.bliss.bookingmanagement.domain.model.valueobjects;

public record Description(String value) {
    public Description {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Message cannot be null or blank");
        }
    }
}