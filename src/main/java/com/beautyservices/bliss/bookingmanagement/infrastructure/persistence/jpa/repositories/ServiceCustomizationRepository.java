package com.beautyservices.bliss.bookingmanagement.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.ServiceCustomization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceCustomizationRepository extends JpaRepository<ServiceCustomization, Long> {
}