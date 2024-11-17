package com.beautyservices.bliss.services.domain.model.commands;

public record UpdateServiceDetailCommand(Long serviceDetailId, Long serviceId, String detail, Long price) {
}
