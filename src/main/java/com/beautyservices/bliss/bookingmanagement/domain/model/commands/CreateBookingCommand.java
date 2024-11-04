package com.beautyservices.bliss.bookingmanagement.domain.model.commands;

public record CreateBookingCommand(Long customerId, Long serviceId, String date, String time, String status, String fullName, String email, String service, String availability, String message, String requirements) {
}
