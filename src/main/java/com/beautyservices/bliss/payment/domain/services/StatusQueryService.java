package com.beautyservices.bliss.payment.domain.services;

import com.beautyservices.bliss.payment.domain.model.entities.Status;
import com.beautyservices.bliss.payment.domain.model.queries.GetStatusByNameQuery;

import java.util.Optional;

public interface StatusQueryService {
    Optional<Status> handle(GetStatusByNameQuery query);
}
