package com.beautyservices.bliss.services.interfaces.rest.transform;

import com.beautyservices.bliss.services.domain.model.commands.UpdateServiceCommand;
import com.beautyservices.bliss.services.interfaces.rest.resources.ServiceResource;

public class UpdateServiceCommandFromResourceAssembler {
    public static UpdateServiceCommand toCommandFromResource(Long serviceId, ServiceResource resource) {
        return new UpdateServiceCommand(serviceId, resource.name(), resource.imageUrl(), resource.description(), resource.basePrice());
    }
}
