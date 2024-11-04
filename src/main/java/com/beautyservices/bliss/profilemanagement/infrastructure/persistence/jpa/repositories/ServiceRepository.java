package com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Servicess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Servicess, Long> {
    boolean existsById(Long id);
    Optional<Servicess> findById(Long id);
}
