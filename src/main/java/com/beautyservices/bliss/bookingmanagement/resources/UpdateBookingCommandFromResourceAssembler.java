package com.beautyservices.bliss.bookingmanagement.resources;

import com.beautyservices.bliss.bookingmanagement.domain.model.commands.UpdateBookingCommand;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources.BookingResource;
import org.springframework.stereotype.Component;

@Component
public class UpdateBookingCommandFromResourceAssembler {
    public static UpdateBookingCommand toCommand(Long id, BookingResource resource) {
        return new UpdateBookingCommand(
                id,
                resource.getCustomerId(),
                resource.getServiceId(),
                resource.getDate(),
                resource.getTime(),
                resource.getStatus(),
                resource.getFullName(),
                resource.getEmail(),
                resource.getService(),
                resource.getAvailability(),
                resource.getMessage(),
                resource.getRequirements()
        );
    }
}