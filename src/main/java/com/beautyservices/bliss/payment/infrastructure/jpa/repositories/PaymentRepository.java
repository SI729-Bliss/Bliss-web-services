package com.beautyservices.bliss.payment.infrastructure.jpa.repositories;

import com.beautyservices.bliss.payment.domain.model.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByCustomerId(Long customerId);
    boolean existsByCustomerId(Long customerId);

}
