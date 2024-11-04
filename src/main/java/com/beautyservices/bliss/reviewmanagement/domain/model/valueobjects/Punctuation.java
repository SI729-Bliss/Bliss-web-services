package com.beautyservices.bliss.reviewmanagement.domain.model.valueobjects;

public record Punctuation(int value) {
    public Punctuation {
        if (value < 0 || value > 5) {
            throw new IllegalArgumentException("Punctuation must be between 0 and 5");
        }
    }
    public Punctuation(){
        this(0);
    }
}
