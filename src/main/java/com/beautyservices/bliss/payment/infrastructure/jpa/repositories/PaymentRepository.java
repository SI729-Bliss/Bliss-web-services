package com.beautyservices.bliss.payment.infrastructure.jpa.repositories;

import com.beautyservices.bliss.payment.domain.model.aggregates.Payment;
import com.beautyservices.bliss.payment.domain.model.valueobjects.CustomerId;
import com.beautyservices.bliss.payment.domain.model.valueobjects.ReservationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByCustomerId(CustomerId customerId);
    boolean existsByCustomerId(CustomerId customerId);
    Optional<Payment> findByReservationId(ReservationId reservationId);
}
