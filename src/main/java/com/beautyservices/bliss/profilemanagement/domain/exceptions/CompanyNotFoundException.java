package com.beautyservices.bliss.profilemanagement.domain.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(Long companyId) {
        super("Company with id " + companyId + " does not exist.");
    }
}