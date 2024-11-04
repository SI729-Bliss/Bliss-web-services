package com.beautyservices.bliss.profilemanagement.domain.services;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Servicess;
import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetAllServicesQuery;
import com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories.ServiceRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.List;

@Service
public interface ServicesQueryService {
    List<Servicess> handle(GetAllServicesQuery query);

}


