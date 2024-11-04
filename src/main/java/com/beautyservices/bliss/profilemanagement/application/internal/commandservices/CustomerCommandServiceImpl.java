package com.beautyservices.bliss.profilemanagement.application.internal.commandservices;


import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Customer;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.UpdateCustomerCommand;
import com.beautyservices.bliss.profilemanagement.domain.services.CustomerCommandService;
import com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerCommandServiceImpl implements CustomerCommandService {

    private final CustomerRepository customerRepository;

    public CustomerCommandServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Optional<Customer> handle(UpdateCustomerCommand command) {

        var customerId = command.id();

        if(!this.customerRepository.existsById(customerId)) {
          throw new IllegalArgumentException("Customer with id " + customerId + " does not exist");
        }
        var customerToUpdate = this.customerRepository.findById(customerId).get();
        customerToUpdate.updateInformation(command.name(), command.email(), command.address(), command.phoneNumber());

        try {
            var updatedCustomer = this.customerRepository.save(customerToUpdate);
            return Optional.of(updatedCustomer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating customer profile: " + e.getMessage());
        }
    }
}
