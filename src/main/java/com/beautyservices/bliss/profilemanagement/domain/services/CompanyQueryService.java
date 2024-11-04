package com.beautyservices.bliss.profilemanagement.domain.services;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Company;
import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetCompanyByIdQuery;

import java.util.Optional;

public interface CompanyQueryService {
    Optional<Company> handle(GetCompanyByIdQuery query);
}
