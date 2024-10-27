package com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsById(Long id);
    Optional<Company> findById(Long id);
}
