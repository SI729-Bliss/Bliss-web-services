package com.beautyservices.bliss.services.application.internal.commandservices;

import com.beautyservices.bliss.services.domain.model.aggregates.Service;
import com.beautyservices.bliss.services.domain.model.commands.CreateServiceCommand;
import com.beautyservices.bliss.services.domain.model.commands.DeleteServiceCommand;
import com.beautyservices.bliss.services.domain.model.commands.UpdateServiceCommand;
import com.beautyservices.bliss.services.domain.model.valueobjects.BeautySalonId;
import com.beautyservices.bliss.services.domain.services.EntServiceCommandService;
import com.beautyservices.bliss.services.infrastructure.persistence.jpa.repositories.DetailRepository;
import com.beautyservices.bliss.services.infrastructure.persistence.jpa.repositories.ServiceRepository;

import java.util.Optional;

@org.springframework.stereotype.Service
public class EntServiceCommandServiceImpl implements EntServiceCommandService {

    private final ServiceRepository serviceRepository;
    private final DetailRepository detailRepository;

    public EntServiceCommandServiceImpl(ServiceRepository serviceRepository, DetailRepository detailRepository) {
        this.serviceRepository = serviceRepository;
        this.detailRepository = detailRepository;
    }

    // Create
    @Override
    public Long handle(CreateServiceCommand command) {
        var name = command.name();
        var salonId = new BeautySalonId(command.salonId());

        if (this.serviceRepository.existsByNameAndSalonId(name, salonId)){
            throw new IllegalArgumentException("Service with name " + name + " already exists for salonId " + salonId.beautySalonId());
        }

        var service = new Service(command);
        try {
            this.serviceRepository.save(service);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving service: " + e.getMessage());
        }

        return service.getId();
    }

    // Update
    @Override
    public Optional<Service> handle(UpdateServiceCommand command) {

        var serviceId = command.serviceId();
        var serviceName = command.name();
        var salonId = new BeautySalonId(command.salonId());

        // If the service does not exist, throw an exception
        if (!this.serviceRepository.existsById(serviceId)){
            throw new IllegalArgumentException("Service with id " + serviceId + " does not exist");
        }

        // Validate service name for update consider salon id
        if (this.serviceRepository.existsByNameAndSalonIdAndIdIsNot(serviceName, salonId, serviceId)){
            throw new IllegalArgumentException("You must update with other name, " + serviceName + " already exists for salonId " + salonId.beautySalonId());
        }

        // Get service to update
        var serviceToUpdate = this.serviceRepository.findById(serviceId).get();

        // Update using Aggregate
        serviceToUpdate.updateServiceInformation(command.name(),command.category() ,command.imageUrl(), command.description(), command.basePrice());

        try {
            var updatedService = this.serviceRepository.save(serviceToUpdate);
            return Optional.of(updatedService);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating service: " + e.getMessage());
        }

    }

    // Delete
    @Override
    public void handle(DeleteServiceCommand command) {

        // If the service does not exist, throw and exception
        if (!this.serviceRepository.existsById(command.serviceId())){
            throw new IllegalArgumentException("Service with id " + command.serviceId() + " does not exist");
        }

        // Delete details
        try {
            this.detailRepository.deleteByServiceId(command.serviceId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting details: " + e.getMessage());
        }

        // Then delete the service, if an error occurs, throw an exception
        try {
            this.serviceRepository.deleteById(command.serviceId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting service: " + e.getMessage());
        }

    }
}