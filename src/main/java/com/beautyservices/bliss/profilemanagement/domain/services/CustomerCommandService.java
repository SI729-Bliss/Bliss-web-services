package com.beautyservices.bliss.profilemanagement.domain.services;


import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Customer;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.UpdateCustomerCommand;

import java.util.Optional;

public interface CustomerCommandService {
    Optional<Customer> handle(UpdateCustomerCommand command);

}