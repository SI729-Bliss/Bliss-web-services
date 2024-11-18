package com.beautyservices.bliss.services.interfaces.rest.resources;

public record ServiceResource(Long id, String name, String category, String imageUrl, String description, int basePrice, Long salonId) {
}
