package com.beautyservices.bliss.payment.infrastructure.jpa.repositories;

import com.beautyservices.bliss.payment.domain.model.entities.PaymentMethod;
import com.beautyservices.bliss.payment.domain.model.valueobjects.PaymentMethods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    boolean existsByName(PaymentMethods name);
    Optional<PaymentMethod> findByName(PaymentMethods name);
}
