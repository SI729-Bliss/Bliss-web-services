package com.beautyservices.bliss.profilemanagement.domain.model.valueobjects;


import jakarta.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public record Email(String email) {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public Email {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public Email() {
        this(null);
    }
}
