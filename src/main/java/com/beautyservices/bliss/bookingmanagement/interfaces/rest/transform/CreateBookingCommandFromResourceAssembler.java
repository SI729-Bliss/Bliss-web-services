package com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform;

import com.beautyservices.bliss.bookingmanagement.domain.model.commands.CreateBookingCommand;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources.CreateBookingResource;
import java.math.BigDecimal;


public class CreateBookingCommandFromResourceAssembler {
    public static CreateBookingCommand toCommandFromResource(CreateBookingResource resource) {
        return new CreateBookingCommand(resource.customerId(), resource.serviceId(), resource.companyId(), resource.bookingDate(), resource.bookingTime(), resource.bookingStatus(), resource.requirements(), resource.totalAmount());
    }
}