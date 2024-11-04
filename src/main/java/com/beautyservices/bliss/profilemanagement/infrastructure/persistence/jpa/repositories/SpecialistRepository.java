package com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
    boolean existsById(Long id);
    Optional<Specialist> findById(Long id);
}
