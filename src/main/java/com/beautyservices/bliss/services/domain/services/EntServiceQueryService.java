package com.beautyservices.bliss.services.domain.services;


import com.beautyservices.bliss.services.domain.model.aggregates.Service;
import com.beautyservices.bliss.services.domain.model.queries.GetAllServicesQuery;
import com.beautyservices.bliss.services.domain.model.queries.GetServiceByIdQuery;
import com.beautyservices.bliss.services.domain.model.queries.GetServicesByCategoryQuery;
import com.beautyservices.bliss.services.domain.model.queries.GetServicesBySalonIdQuery;

import java.util.List;
import java.util.Optional;

public interface EntServiceQueryService {
    List<Service> handle(GetAllServicesQuery query);
    Optional<Service> handle(GetServiceByIdQuery query);
    // By salon Id
    List<Service> handle(GetServicesBySalonIdQuery query);
    List<Service> handle(GetServicesByCategoryQuery query);
}