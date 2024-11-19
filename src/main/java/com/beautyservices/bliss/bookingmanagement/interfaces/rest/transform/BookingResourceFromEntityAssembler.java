package com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources.BookingResource;

public class BookingResourceFromEntityAssembler {
    public static BookingResource toResourceFromEntity (Reservation e) {
        return new BookingResource(e.getId(), e.getCustomerId(), e.getServiceId(), e.getCompanyId(), e.getBookingDate(), e.getBookingTime(), e.isBookingStatus(), e.getRequirements(), e.getTotalAmount());
    }
}