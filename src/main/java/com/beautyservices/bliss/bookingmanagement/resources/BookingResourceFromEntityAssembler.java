package com.beautyservices.bliss.bookingmanagement.resources;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.BookingService;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources.BookingResource;

public class BookingResourceFromEntityAssembler {
    public static BookingResource toResourceFromEntity(BookingService bookingService) {
        BookingResource resource = new BookingResource();
        resource.setId(bookingService.getId());
        resource.setCustomerId(bookingService.getCustomerId());
        resource.setServiceId(bookingService.getServiceId());
        resource.setDate(bookingService.getDate());
        resource.setTime(bookingService.getTime());
        resource.setStatus(bookingService.getStatus());
        resource.setFullName(bookingService.getFullName());
        resource.setEmail(bookingService.getEmail());
        resource.setService(bookingService.getService());
        resource.setAvailability(bookingService.getAvailability());
        resource.setMessage(bookingService.getMessage());
        resource.setRequirements(bookingService.getRequirements());
        resource.setCreatedDate(bookingService.getCreatedDate() != null ? bookingService.getCreatedDate().toString() : null);
        return resource;
    }
}