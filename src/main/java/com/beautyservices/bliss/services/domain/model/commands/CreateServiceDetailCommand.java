package com.beautyservices.bliss.services.domain.model.commands;

public record CreateServiceDetailCommand(Long serviceId, String detail, Long price) {
}
