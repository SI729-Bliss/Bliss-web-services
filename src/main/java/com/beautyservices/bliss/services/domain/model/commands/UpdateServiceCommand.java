package com.beautyservices.bliss.services.domain.model.commands;

public record UpdateServiceCommand(Long serviceId, String name, String imageUrl, String description, int basePrice) {
}
