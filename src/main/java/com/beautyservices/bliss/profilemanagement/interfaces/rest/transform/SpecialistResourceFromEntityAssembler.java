package com.beautyservices.bliss.profilemanagement.interfaces.rest.transform;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Specialist;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.SpecialistResource;

public class SpecialistResourceFromEntityAssembler {
    public static SpecialistResource toResourceFromEntity(Specialist entity) {
        return new SpecialistResource(entity.getId(), entity.getName(), entity.getSpecialism(),entity.getImage(), entity.getRating());
    }
}
