package com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingResource {
    private Long id;
    private Long customerId;
    private Long serviceId;
    private Long companyId;
    private String bookingDate;
    private String bookingTime;
    private boolean bookingStatus;
    private List<String> requirements;
}