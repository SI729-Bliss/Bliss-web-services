package com.beautyservices.bliss.services.application.internal.queryservices;

import com.beautyservices.bliss.services.domain.model.entities.ServiceDetail;
import com.beautyservices.bliss.services.domain.model.queries.GetDetailByIdQuery;
import com.beautyservices.bliss.services.domain.model.queries.GetDetailsByServiceIdQuery;
import com.beautyservices.bliss.services.domain.services.DetailQueryService;
import com.beautyservices.bliss.services.infrastructure.persistence.jpa.repositories.DetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailQueryServiceImpl implements DetailQueryService {

    private final DetailRepository detailRepository;

    public DetailQueryServiceImpl(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    public Optional<ServiceDetail> handle(GetDetailByIdQuery query) {
        return this.detailRepository.findById(query.serviceDetailId());
    }

    @Override
    public List<ServiceDetail> handle(GetDetailsByServiceIdQuery query) {
        return this.detailRepository.findByServiceId(query.serviceId());
    }
}
