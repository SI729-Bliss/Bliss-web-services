package com.beautyservices.bliss.services.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record BeautySalonId(Long beautySalonId) {
    public BeautySalonId {
        if (beautySalonId == null || beautySalonId < 0) {
            throw new IllegalArgumentException("Salon id cannot be null");
        }
    }

}
