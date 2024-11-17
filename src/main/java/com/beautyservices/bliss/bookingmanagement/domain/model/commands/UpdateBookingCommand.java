package com.beautyservices.bliss.bookingmanagement.domain.model.commands;

import java.util.List;

public record UpdateBookingCommand(Long id, boolean bookingStatus, List<String> requirements) {
}