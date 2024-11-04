package com.beautyservices.bliss.bookingmanagement.domain.services;

import com.beautyservices.bliss.bookingmanagements.domain.model.aggregates.ServiceCustomization;
import com.beautyservices.bliss.bookingmanagements.infrastructure.persistence.jpa.repositories.ServiceCustomizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCustomizationService {
    private final ServiceCustomizationRepository serviceCustomizationRepository;

    public ServiceCustomizationService(ServiceCustomizationRepository serviceCustomizationRepository) {
        this.serviceCustomizationRepository = serviceCustomizationRepository;
    }

    public List<ServiceCustomization> getAllServiceCustomizations() {
        return serviceCustomizationRepository.findAll();
    }

    public ServiceCustomization saveServiceCustomization(ServiceCustomization serviceCustomization) {
        return serviceCustomizationRepository.save(serviceCustomization);
    }
}