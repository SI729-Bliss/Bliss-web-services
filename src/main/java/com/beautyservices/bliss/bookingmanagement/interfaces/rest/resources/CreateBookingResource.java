package com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class CreateBookingResource {
    private Long customerId;
    private Long serviceId;
    private Long companyId;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private boolean bookingStatus;
    private List<String> requirements;
    private BigDecimal totalPrice;
    private ServiceInfo serviceInfo;

    @Getter
    @Setter
    public static class ServiceInfo {
        private BigDecimal basePrice;
    }
}