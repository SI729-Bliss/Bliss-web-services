package com.beautyservices.bliss.services.interfaces.rest.transform;

import com.beautyservices.bliss.services.domain.model.commands.UpdateServiceDetailCommand;
import com.beautyservices.bliss.services.interfaces.rest.resources.DetailResource;

public class UpdateDetailCommandFromResourceAssembler {
    public static UpdateServiceDetailCommand toCommandFromResource(Long detailId, DetailResource resource){
        return new UpdateServiceDetailCommand(detailId, resource.serviceId(), resource.detail(), resource.price());
    }
}
