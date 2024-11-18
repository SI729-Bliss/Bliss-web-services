package com.beautyservices.bliss.profilemanagement.application.internal.queryservices;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Company;
import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetCompanyByIdQuery;
import com.beautyservices.bliss.profilemanagement.domain.services.CompanyQueryService;
import com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyQueryServiceImpl implements CompanyQueryService {

    private final CompanyRepository companyRepository;

    public CompanyQueryServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<Company> handle(GetCompanyByIdQuery query) {
        return this.companyRepository.findById(query.Id());


    }

}