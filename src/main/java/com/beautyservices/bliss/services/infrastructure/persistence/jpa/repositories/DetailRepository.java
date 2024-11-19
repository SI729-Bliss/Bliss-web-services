package com.beautyservices.bliss.services.infrastructure.persistence.jpa.repositories;

import com.beautyservices.bliss.services.domain.model.entities.ServiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<ServiceDetail, Long> {

    boolean existsByDetailAndServiceId(String detail, Long serviceId);

    boolean existsByDetailAndServiceIdAndIdIsNot(String detail, Long serviceId, Long id);

    List<ServiceDetail> findByServiceId(Long id);

    @Transactional
    void deleteByServiceId(Long serviceId);

}
