package com.beautyservices.bliss.profilemanagement.interfaces.rest.transform;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Company;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CompanyResource;

public class CompanyResourceFromEntityAssembler {
    public static CompanyResource toResourceFromEntity(Company entity){
        return new CompanyResource(entity.getId(), entity.getName(), entity.getEmail(), entity.getAddress(), entity.getPhoneNumber(), entity.getRating());
    }
}
