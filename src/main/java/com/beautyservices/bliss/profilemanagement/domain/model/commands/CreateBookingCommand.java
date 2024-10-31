package com.beautyservices.bliss.profilemanagement.domain.model.commands;

public record CreateBookingCommand(Long customerId, Long serviceId, String date, String time, String status, String fullName, String email, String service, String availability, String message, String requirements) {
}
