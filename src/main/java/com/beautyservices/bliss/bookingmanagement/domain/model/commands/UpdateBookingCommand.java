package com.beautyservices.bliss.bookingmanagement.domain.model.commands;

import java.math.BigDecimal;
import java.util.List;

public record UpdateBookingCommand(Long id,
                                   boolean bookingStatus,
                                   List<String> requirements,
                                   float totalAmount) {
}