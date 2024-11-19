package com.beautyservices.bliss.profilemanagement.interfaces.rest.transform;

import com.beautyservices.bliss.profilemanagement.domain.model.commands.CreateCompanyCommand;

import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CreateCompanyResource;

public class CreateCompanyCommandFromResourceAssembler {
    public static CreateCompanyCommand toCommand(CreateCompanyResource resource){
        return new CreateCompanyCommand(resource.id(), resource.name(), resource.email(), resource.address(), resource.phoneNumber(), resource.rating());
    }
}
