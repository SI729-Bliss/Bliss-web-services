package com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record CreateBookingResource(Long customerId,
                                    Long serviceId,
                                    Long companyId,
                                    LocalDate bookingDate,
                                    String bookingTime,
                                    boolean bookingStatus,
                                    List<String> requirements,
                                    float totalAmount) {

}