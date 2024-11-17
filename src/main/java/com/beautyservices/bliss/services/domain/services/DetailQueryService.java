package com.beautyservices.bliss.services.domain.services;

import com.beautyservices.bliss.services.domain.model.entities.ServiceDetail;
import com.beautyservices.bliss.services.domain.model.queries.GetDetailByIdQuery;
import com.beautyservices.bliss.services.domain.model.queries.GetDetailsByServiceIdQuery;

import java.util.List;
import java.util.Optional;

public interface DetailQueryService {

    Optional<ServiceDetail> handle(GetDetailByIdQuery query);

    // By serviceId
    List<ServiceDetail> handle(GetDetailsByServiceIdQuery query);
}
