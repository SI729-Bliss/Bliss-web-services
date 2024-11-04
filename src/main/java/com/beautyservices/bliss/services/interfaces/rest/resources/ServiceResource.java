package com.beautyservices.bliss.services.interfaces.rest.resources;

public record ServiceResource(Long id, String name, Long categoryId, String imageUrl, String description, int basePrice, Long beautySalonId) {
}
