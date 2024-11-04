package com.beautyservices.bliss.services.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.services.domain.model.aggregates.Service;
import com.beautyservices.bliss.services.domain.model.valueobjects.BeautySalonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    boolean existsByName(String name); // Validate existing names

    boolean existsByNameAndIdIsNot(String name, Long id);

    // Optional<Service> findByName(String name);

    //List<Service> getAllBySalonId(BeautySalonId salonId); // List by salonId
    List<Service> findBySalonId_BeautySalonId(Long beautySalonId);

    //long countAllBySalonId(Long beautySalonId); // Count services by salonId
}
