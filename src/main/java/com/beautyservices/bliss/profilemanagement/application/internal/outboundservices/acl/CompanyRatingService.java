package com.beautyservices.bliss.profilemanagement.application.internal.outboundservices.acl;

import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Company;
import com.beautyservices.bliss.profilemanagement.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyRatingService {

    @Autowired
    private CompanyRepository companyRepository;

    public void updateCompanyRating(Long companyId, int newPunctuation) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new IllegalArgumentException("Company not found"));
        double currentRating = company.getRating();
        double updatedRating = (currentRating + newPunctuation) / 2; // Simplified rating calculation
        company.setRating(updatedRating);
        companyRepository.save(company);
    }
}