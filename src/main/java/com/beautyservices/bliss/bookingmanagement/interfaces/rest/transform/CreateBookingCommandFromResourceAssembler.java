package com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform;

import com.beautyservices.bliss.bookingmanagement.domain.model.commands.CreateBookingCommand;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources.CreateBookingResource;

public class CreateBookingCommandFromResourceAssembler {
    public static CreateBookingCommand toCommand(CreateBookingResource resource) {
        return new CreateBookingCommand(
                resource.getCustomerId(),
                resource.getServiceId(),
                resource.getCompanyId(),
                resource.getBookingDate(),
                resource.getBookingTime(),
                resource.isBookingStatus(),
                resource.getRequirements()
        );
    }
}