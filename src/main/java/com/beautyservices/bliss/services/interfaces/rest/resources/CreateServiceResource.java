package com.beautyservices.bliss.services.interfaces.rest.resources;

public record CreateServiceResource(String name, String category, String imageUrl, String description, int basePrice, Long salonId) {
}
