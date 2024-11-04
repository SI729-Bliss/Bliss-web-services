package com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform;

public class SpecialistResourceFromEntityAssembler {
    public static SpecialistResource toResourceFromEntity(Specialist entity) {
        return new SpecialistResource(entity.getId(), entity.getName(), entity.getSpecialism(),entity.getImage(), entity.getRating());
    }
}
