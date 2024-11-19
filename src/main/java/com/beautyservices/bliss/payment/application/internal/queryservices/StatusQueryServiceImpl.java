package com.beautyservices.bliss.payment.application.internal.queryservices;

import com.beautyservices.bliss.payment.domain.model.entities.Status;
import com.beautyservices.bliss.payment.domain.model.queries.GetStatusByNameQuery;
import com.beautyservices.bliss.payment.domain.services.StatusQueryService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class StatusQueryServiceImpl  implements StatusQueryService {
    private final StatusRepository statusRepository;

    public StatusQueryServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Optional<Status> handle(GetStatusByNameQuery query) {
        return this.statusRepository.findByName(query.name());
    }
}
