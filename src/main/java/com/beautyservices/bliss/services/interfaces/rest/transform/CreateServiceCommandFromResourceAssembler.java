package com.beautyservices.bliss.services.interfaces.rest.transform;

import com.beautyservices.bliss.services.domain.model.commands.CreateServiceCommand;
import com.beautyservices.bliss.services.interfaces.rest.resources.CreateServiceResource;

public class CreateServiceCommandFromResourceAssembler {
    public static CreateServiceCommand toCommandFromResource(CreateServiceResource resource) {
        return new CreateServiceCommand(resource.name(), resource.categoryId(), resource.imageUrl(), resource.description(), resource.basePrice(), resource.salonId());
    }
}
