package com.beautyservices.bliss.bookingmanagement.domain.model.commands;

import java.util.List;

import java.time.LocalDate;

public record CreateBookingCommand(Long customerId,
                                   Long serviceId,
                                   Long companyId,
                                   LocalDate bookingDate,
                                   String bookingTime,
                                   boolean bookingStatus,
                                   List<String> requirements,
                                   float  totalAmount) {
}