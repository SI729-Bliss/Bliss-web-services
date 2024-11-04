package com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform;

public class ServiceResourceFromEntityAssembler {
    public static ServiceResource toResourceFromEntity(Servicess entity) {
        return new ServiceResource(entity.getId(), entity.getName(), entity.getServiceCategory(),entity.getDescription(),entity.getRating(), entity.getPrice());
    }
}
