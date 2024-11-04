package com.beautyservices.bliss.services.domain.model.commands;

public record CreateServiceCommand(String name, String imageUrl, String description, int basePrice) {
}
