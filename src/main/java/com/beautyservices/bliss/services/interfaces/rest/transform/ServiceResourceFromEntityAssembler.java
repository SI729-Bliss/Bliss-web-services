package com.beautyservices.bliss.services.interfaces.rest.transform;

import com.beautyservices.bliss.services.domain.model.aggregates.Service;
import com.beautyservices.bliss.services.interfaces.rest.resources.ServiceResource;

public class ServiceResourceFromEntityAssembler {
    public static ServiceResource toResourceFromEntity(Service entity) {
        return new ServiceResource(entity.getId(), entity.getName(), entity.getCategoryId(), entity.getImageUrl(), entity.getDescription(), entity.getBasePrice(), entity.getSalonId());
    }
    //ServiceResource(Long id, String name, int categoryId, String imageUrl, String description, int basePrice, int beautySalonId)
}
