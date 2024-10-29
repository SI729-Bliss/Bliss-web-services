package com.beautyservices.bliss.services.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.services.domain.model.aggregates.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    boolean existsByName(String name); // Validate existing names
    // boolean existsByNameAndIdNot(String name, Long id); // Validate to update service
    // Optional<Service> findByName(String name);
    List<Service> getBySalonId(Long salonId); // List by salonId
}
