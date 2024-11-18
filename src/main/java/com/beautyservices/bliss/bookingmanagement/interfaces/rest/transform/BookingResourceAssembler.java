package com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources.BookingResource;

public class BookingResourceAssembler {
    public static BookingResource toResource(Reservation reservation) {
        BookingResource resource = new BookingResource();
        resource.setId(reservation.getId());
        resource.setCustomerId(reservation.getCustomerId());
        resource.setServiceId(reservation.getService().getId());
        resource.setCompanyId(reservation.getCompany().getId());
        resource.setBookingDate(reservation.getBookingDate());
        resource.setBookingTime(reservation.getBookingTime());
        resource.setBookingStatus(reservation.isBookingStatus());
        resource.setRequirements(reservation.getRequirements());
        resource.setTotalPrice(reservation.getTotalPrice());

        BookingResource.ServiceInfo serviceInfo = new BookingResource.ServiceInfo();
        serviceInfo.setBasePrice(reservation.getServiceInfo().getBasePrice());
        resource.setServiceInfo(serviceInfo);

        return resource;
    }
}