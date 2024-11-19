package com.beautyservices.bliss.services.interfaces.rest.transform;

import com.beautyservices.bliss.services.domain.model.entities.ServiceDetail;
import com.beautyservices.bliss.services.interfaces.rest.resources.DetailResource;

public class DetailResourceFromEntityAssembler {
    public static DetailResource toResourceFromEntity(ServiceDetail entity){
        return new DetailResource(entity.getId(), entity.getServiceId(), entity.getDetail(), entity.getPrice());
    }
}
