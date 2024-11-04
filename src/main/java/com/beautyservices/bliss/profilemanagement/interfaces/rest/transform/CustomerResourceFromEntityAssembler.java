package com.beautyservices.bliss.profilemanagement.interfaces.rest.transform;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Customer;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CustomerResource;

public class CustomerResourceFromEntityAssembler {
    public static CustomerResource toResourceFromEntity(Customer customer) {
        return new CustomerResource(customer.getId(), customer.getName(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber());
    }
}
