package com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResource {
    private Long id;
    private Long customerId;
    private Long serviceId;
    private String date;
    private String time;
    private String status;
    private String fullName;
    private String email;
    private String service;
    private String availability;
    private String message;
    private String requirements;
    private String createdDate;
}