package com.beautyservices.bliss.services.domain.model.commands;

public record CreateServiceCommand(String name, String category, String imageUrl, String description, int basePrice, Long salonId) {
}
