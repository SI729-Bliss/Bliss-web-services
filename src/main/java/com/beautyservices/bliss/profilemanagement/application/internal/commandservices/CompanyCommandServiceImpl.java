package com.beautyservices.bliss.profilemanagement.application.internal.commandservices;

import com.beautyservices.bliss.profilemanagement.application.internal.outboundservices.acl.ExternalIamService;
import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Company;
import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Customer;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.CreateCompanyCommand;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.UpdateCompanyCommand;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.UpdateCustomerCommand;
import com.beautyservices.bliss.profilemanagement.domain.services.CompanyCommandService;
import com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyCommandServiceImpl implements CompanyCommandService {

    private final CompanyRepository companyRepository;
    private final ExternalIamService externalIamService;

    public CompanyCommandServiceImpl(CompanyRepository companyRepository, ExternalIamService externalIamService) {
        this.companyRepository = companyRepository;
        this.externalIamService = externalIamService;
    }


    @Override
    public Optional<Company> handle(UpdateCompanyCommand command) {

        var customerId = command.id();

        if(!this.companyRepository.existsById(customerId)) {
            throw new IllegalArgumentException("Customer with id " + customerId + " does not exist");
        }
        var customerToUpdate = this.companyRepository.findById(customerId).get();
        customerToUpdate.updateInformation(command.name(), command.email(), command.address(), command.phoneNumber(), command.rating());

        try {
            var updatedCustomer = this.companyRepository.save(customerToUpdate);
            return Optional.of(updatedCustomer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating customer profile: " + e.getMessage());
        }
    }
    @Override
    public Long handle(CreateCompanyCommand command) {
        if (this.companyRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Company with id " + command.id() + " already exists");
        }
        var company = new Company(command);
        try {
            this.companyRepository.save(company);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving company profile: " + e.getMessage());
        }
        return company.getId();
    }


}
