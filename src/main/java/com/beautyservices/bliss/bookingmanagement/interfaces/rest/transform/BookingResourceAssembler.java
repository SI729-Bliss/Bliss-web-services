package com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources.BookingResource;

public class BookingResourceAssembler {
    public static BookingResource toResource(Reservation reservation) {
        BookingResource resource = new BookingResource();
        resource.setId(reservation.getId());
        resource.setCustomerId(reservation.getCustomerId());
        resource.setServiceId(reservation.getServiceId());
        resource.setCompanyId(reservation.getCompanyId());
        resource.setBookingDate(reservation.getBookingDate());
        resource.setBookingTime(reservation.getBookingTime());
        resource.setBookingStatus(reservation.isBookingStatus());
        resource.setRequirements(reservation.getRequirements());
        return resource;
    }
}