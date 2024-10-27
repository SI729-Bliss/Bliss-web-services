package com.beautyservices.bliss.profilemanagement.application.internal.commandservices;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Company;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.UpdateCompanyCommand;
import com.beautyservices.bliss.profilemanagement.domain.services.CompanyCommandService;
import com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyCommandServiceImpl implements CompanyCommandService {

    private final CompanyRepository companyRepository;

    public CompanyCommandServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public Optional<Company> handle(UpdateCompanyCommand command) {

        var companyId = command.id();

        if (!this.companyRepository.existsById(companyId)) {
            throw new IllegalArgumentException("Profile with id " + companyId + " does not exist");
        }

        var companyToUpdate = this.companyRepository.findById(companyId).get();
        companyToUpdate.updateInformation(command.name(), command.email(), command.address(), command.phoneNumber());

        try {
            var updatedCompany = this.companyRepository.save(companyToUpdate);
            return Optional.of(updatedCompany);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating profile: " + e.getMessage());
        }

    }
}
