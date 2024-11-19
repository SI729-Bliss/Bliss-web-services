package com.beautyservices.bliss.services.domain.model.commands;

public record UpdateServiceCommand(Long serviceId, String name,String category, String imageUrl, String description, int basePrice, Long salonId) {
}
