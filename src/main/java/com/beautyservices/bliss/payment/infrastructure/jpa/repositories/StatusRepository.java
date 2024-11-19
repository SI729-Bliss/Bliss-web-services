package com.beautyservices.bliss.payment.infrastructure.jpa.repositories;

import com.beautyservices.bliss.payment.domain.model.entities.Status;
import com.beautyservices.bliss.payment.domain.model.valueobjects.Statuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface StatusRepository extends JpaRepository<Status, Long> {
    boolean existsByName(Statuses name);
    Optional<Status> findByName(Statuses name);
}
