package com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReservationResource {
    private Long id;
    private Long customerId;
    private Long serviceId;
    private Long beautySalonId;
    private String reservationDate;
    private String reservationTime;
    private String status;
    private Float totalPrice;
    private List<String> customizations;
}
