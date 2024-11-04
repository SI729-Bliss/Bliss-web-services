package com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform;

public class CustomerResourceFromEntityAssembler {
    public static CustomerResource toResourceFromEntity(Customer customer) {
        return new CustomerResource(customer.getId(), customer.getName(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber());
    }
}
