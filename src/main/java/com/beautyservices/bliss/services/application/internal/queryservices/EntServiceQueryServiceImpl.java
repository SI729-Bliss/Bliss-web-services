package com.beautyservices.bliss.services.application.internal.queryservices;

import com.beautyservices.bliss.services.domain.model.aggregates.Service;
import com.beautyservices.bliss.services.domain.model.queries.GetAllServicesQuery;
import com.beautyservices.bliss.services.domain.model.queries.GetServiceByIdQuery;
import com.beautyservices.bliss.services.domain.model.queries.GetServicesByCategoryQuery;
import com.beautyservices.bliss.services.domain.model.queries.GetServicesBySalonIdQuery;
import com.beautyservices.bliss.services.domain.services.EntServiceQueryService;
import com.beautyservices.bliss.services.infrastructure.persistence.jpa.repositories.ServiceRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class EntServiceQueryServiceImpl implements EntServiceQueryService {

    private final ServiceRepository serviceRepository;

    public EntServiceQueryServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<Service> handle(GetAllServicesQuery query){
        return this.serviceRepository.findAll();
    }

    @Override
    public Optional<Service> handle(GetServiceByIdQuery query) {
        return this.serviceRepository.findById(query.serviceId());
    }

    // Implement find by salonId
    @Override
    public List<Service> handle(GetServicesBySalonIdQuery query) {
        return this.serviceRepository.findBySalonId(query.beautySalonId());
    }

    @Override
    public List<Service> handle(GetServicesByCategoryQuery query) {
        return this.serviceRepository.findByCategory(query.category());
    }

}