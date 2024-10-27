package com.beautyservices.bliss.profilemanagement.domain.services;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Specialist;
import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetAllSpecialistsQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpecialistQueryService {
    List<Specialist> handle(GetAllSpecialistsQuery query);
}


