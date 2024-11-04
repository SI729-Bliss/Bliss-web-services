package com.beautyservices.bliss.services.domain.model.valueobjects;

public record CategoryId(Long categoryId) {
    public CategoryId {
        if (categoryId <= 0){
            throw new IllegalArgumentException("Category id cannot be negative or 0");
        }
    }

    public CategoryId() {
        this(0L);
    }
}
