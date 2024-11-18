package com.beautyservices.bliss.services.interfaces.rest.transform;

import com.beautyservices.bliss.services.domain.model.commands.CreateServiceDetailCommand;
import com.beautyservices.bliss.services.interfaces.rest.resources.CreateDetailResource;

public class CreateDetailCommandFromResourceAssembler {
    public static CreateServiceDetailCommand toCommandFromResource(CreateDetailResource resource){
        return new CreateServiceDetailCommand(resource.serviceId(), resource.detail(), resource.price());
    }
}
