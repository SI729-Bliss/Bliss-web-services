package com.beautyservices.bliss.profilemanagement.interfaces.rest.transform;

import com.beautyservices.bliss.profilemanagement.domain.model.commands.UpdateCompanyCommand;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CustomerResource;

public class UpdateCustomerCommandFromResourceAssembler {
    public static UpdateCompanyCommand toCommandFromResource(Long id, CustomerResource resource) {
        return new UpdateCompanyCommand(id, resource.name(), resource.email(), resource.phoneNumber(), resource.address());
    }
}
