package com.beautyservices.bliss.profilemanagement.application.internal.queryservices;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Customer;
import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetCustomerByIdQuery;
import com.beautyservices.bliss.profilemanagement.domain.services.CustomerQueryService;
import com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerQueryServiceImpl implements CustomerQueryService {

    private final CustomerRepository customerRepository;

    public CustomerQueryServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Optional<Customer> handle(GetCustomerByIdQuery query) {
        return this.customerRepository.findById(query.id());
    }

}
