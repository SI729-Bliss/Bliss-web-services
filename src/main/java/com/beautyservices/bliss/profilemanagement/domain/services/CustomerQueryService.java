package com.beautyservices.bliss.profilemanagement.domain.services;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Customer;
import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetCustomerByIdQuery;

import java.util.Optional;

public interface CustomerQueryService {
    Optional<Customer> handle(GetCustomerByIdQuery query);

}