package com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform;

public class CompanyResourceFromEntityAssembler {
    public static CompanyResource toResourceFromEntity(Company entity){
        return new CompanyResource(entity.getId(), entity.getName(), entity.getEmail(), entity.getAddress(), entity.getPhoneNumber());
    }
}
