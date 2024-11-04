package com.beautyservices.bliss.bookingmanagement.domain.services;

import com.beautyservices.bliss.bookingmanagements.domain.model.aggregates.Service;
import com.beautyservices.bliss.bookingmanagements.infrastructure.persistence.jpa.repositories.BookingServiceRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {
    private final BookingServiceRepository serviceRepository;

    public ServiceService(BookingServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Service saveService(Service service) {
        return serviceRepository.save(service);
    }
}