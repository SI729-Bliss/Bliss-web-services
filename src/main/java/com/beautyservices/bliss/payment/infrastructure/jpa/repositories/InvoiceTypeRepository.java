package com.beautyservices.bliss.payment.infrastructure.jpa.repositories;

import com.beautyservices.bliss.payment.domain.model.entities.InvoiceType;
import com.beautyservices.bliss.payment.domain.model.valueobjects.InvoiceTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface InvoiceTypeRepository extends JpaRepository<InvoiceType, Long> {
    boolean existsByName(InvoiceTypes name);
    Optional<InvoiceType> findByName(InvoiceTypes name);
}
