package com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform;

public class UpdateCompanyCommandFromResourceAssembler {
    public static UpdateCompanyCommand toCommandFromResource(Long id, CompanyResource resource) {
        return new UpdateCompanyCommand(id, resource.name(), resource.email(), resource.phoneNumber(), resource.address());
    }
}
