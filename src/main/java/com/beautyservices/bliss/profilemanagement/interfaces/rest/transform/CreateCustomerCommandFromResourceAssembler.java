package com.beautyservices.bliss.profilemanagement.interfaces.rest.transform;

import com.beautyservices.bliss.profilemanagement.domain.model.commands.CreateCompanyCommand;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.CreateCustomerCommand;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CreateCompanyResource;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CreateCustomerResource;

public class CreateCustomerCommandFromResourceAssembler {
    public static CreateCustomerCommand toCommand(CreateCustomerResource resource) {
        return new CreateCustomerCommand(
                resource.id(),
                resource.name(), resource.email(), resource.address(), resource.phoneNumber());
    }
}