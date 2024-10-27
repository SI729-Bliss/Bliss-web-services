package com.beautyservices.bliss.profilemanagement.interfaces.rest.transform;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Servicess;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.ServiceResource;

public class ServiceResourceFromEntityAssembler {
    public static ServiceResource toResourceFromEntity(Servicess entity) {
        return new ServiceResource(entity.getId(), entity.getName(), entity.getServiceCategory(),entity.getDescription(),entity.getRating(), entity.getPrice());
    }
}
