package com.beautyservices.bliss.bookingmanagement.domain.model.commands;

import java.time.LocalTime;
import java.util.List;

import java.time.LocalDate;

public record CreateBookingCommand(Long customerId,
                                   Long serviceId,
                                   Long companyId,
                                   LocalDate bookingDate,
                                   LocalTime bookingTime,
                                   boolean bookingStatus,
                                   List<String> requirements,
                                   float  totalAmount) {
}