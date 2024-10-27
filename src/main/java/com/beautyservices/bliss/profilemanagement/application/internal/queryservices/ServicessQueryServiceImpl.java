package com.beautyservices.bliss.profilemanagement.application.internal.queryservices;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Servicess;
import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetAllServicesQuery;
import com.beautyservices.bliss.profilemanagement.domain.services.ServicesQueryService;
import com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicessQueryServiceImpl implements ServicesQueryService {

    private final ServiceRepository serviceRepository;

    public ServicessQueryServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<Servicess> handle(GetAllServicesQuery query) {
        return this.serviceRepository.findAll();
    }
}
