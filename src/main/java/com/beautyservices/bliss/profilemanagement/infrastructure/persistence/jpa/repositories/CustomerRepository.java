package com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Company;
import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Customer;
import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetCustomerByIdQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsById(Long id);
    Optional<Customer> findById(Long id);

}
