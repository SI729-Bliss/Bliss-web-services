package com.beautyservices.bliss.profilemanagement.interfaces.rest.transform;

import com.beautyservices.bliss.profilemanagement.domain.model.commands.UpdateCustomerCommand;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CustomerResource;

public class UpdateCustomerCommandFromResourceAssembler {
    public static UpdateCustomerCommand toCommandFromResource(Long id, CustomerResource resource) {
        return new UpdateCustomerCommand(id, resource.name(), resource.email(), resource.phoneNumber(), resource.address());
    }
}