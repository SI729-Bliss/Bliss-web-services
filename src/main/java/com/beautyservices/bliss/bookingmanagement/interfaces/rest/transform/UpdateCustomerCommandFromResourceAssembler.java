package com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform;

public class UpdateCustomerCommandFromResourceAssembler {
    public static UpdateCustomerCommand toCommandFromResource(Long id, CustomerResource resource) {
        return new UpdateCustomerCommand(id, resource.name(), resource.email(), resource.phoneNumber(), resource.address());
    }
}
