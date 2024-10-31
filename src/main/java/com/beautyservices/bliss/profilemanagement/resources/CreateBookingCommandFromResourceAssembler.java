package com.beautyservices.bliss.profilemanagement.resources;

import com.beautyservices.bliss.profilemanagement.domain.model.commands.CreateBookingCommand;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CreateBookingResource;
import org.springframework.stereotype.Component;

@Component
public class CreateBookingCommandFromResourceAssembler {
    public CreateBookingCommand toCommand(CreateBookingResource resource) {
        return new CreateBookingCommand(
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