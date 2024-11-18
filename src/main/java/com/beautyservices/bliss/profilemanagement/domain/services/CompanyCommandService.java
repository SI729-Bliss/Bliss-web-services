package com.beautyservices.bliss.profilemanagement.domain.services;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Company;

import com.beautyservices.bliss.profilemanagement.domain.model.commands.UpdateCompanyCommand;

import java.util.Optional;

public interface CompanyCommandService {
    Optional<Company> handle(UpdateCompanyCommand command);

}