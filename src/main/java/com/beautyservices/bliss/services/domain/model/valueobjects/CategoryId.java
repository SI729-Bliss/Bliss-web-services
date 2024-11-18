package com.beautyservices.bliss.services.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CategoryId(Long categoryId) {
    public CategoryId {
        if (categoryId == null || categoryId < 0){
            throw new IllegalArgumentException("Category id cannot be null");
        }
    }
}