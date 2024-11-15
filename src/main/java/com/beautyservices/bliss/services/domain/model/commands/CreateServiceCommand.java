package com.beautyservices.bliss.services.domain.model.commands;

public record CreateServiceCommand(String name, Long categoryId, String imageUrl, String description, int basePrice, Long salonId) {
}
