package com.beautyservices.bliss.profilemanagement.application.internal.queryservices;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Specialist;
import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetAllSpecialistsQuery;
import com.beautyservices.bliss.profilemanagement.domain.services.SpecialistQueryService;
import com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories.SpecialistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialistQueryServiceImpl implements SpecialistQueryService {

    private final SpecialistRepository specialistRepository;

    public SpecialistQueryServiceImpl(SpecialistRepository specialistRepository) {
        this.specialistRepository = specialistRepository;
    }

    @Override
    public List<Specialist> handle(GetAllSpecialistsQuery query) {
        return this.specialistRepository.findAll();
    }
}