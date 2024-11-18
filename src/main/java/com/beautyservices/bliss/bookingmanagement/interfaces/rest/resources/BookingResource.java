package com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record BookingResource(Long id,
                              Long customerId,
                              Long serviceId,
                              Long companyId,
                              LocalDate bookingDate,
                              String bookingTime,
                              boolean bookingStatus,
                              List<String> requirements,
                              float totalAmount) {
}