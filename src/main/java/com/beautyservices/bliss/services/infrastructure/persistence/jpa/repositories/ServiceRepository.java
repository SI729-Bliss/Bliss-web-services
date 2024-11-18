package com.beautyservices.bliss.services.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.services.domain.model.aggregates.Service;
import com.beautyservices.bliss.services.domain.model.valueobjects.BeautySalonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    boolean existsByNameAndSalonId(String name, BeautySalonId salonId);

    boolean existsByNameAndSalonIdAndIdIsNot(String name, BeautySalonId salonId, Long id);

    // List by salonId
    List<Service> findBySalonId(BeautySalonId beautySalonId);

    // List by category
    List<Service> findByCategory(String category);
}
