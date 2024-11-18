package com.beautyservices.bliss.bookingmanagement.domain.model.commands;

import java.util.List;

public record CreateBookingCommand(Long customerId, Long serviceId, Long companyId, String bookingDate, String bookingTime, boolean bookingStatus, List<String> requirements) {
}