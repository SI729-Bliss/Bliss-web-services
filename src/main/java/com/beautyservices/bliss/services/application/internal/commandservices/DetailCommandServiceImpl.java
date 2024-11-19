package com.beautyservices.bliss.services.application.internal.commandservices;

import com.beautyservices.bliss.services.domain.model.commands.CreateServiceDetailCommand;
import com.beautyservices.bliss.services.domain.model.commands.DeleteServiceDetailCommand;
import com.beautyservices.bliss.services.domain.model.commands.UpdateServiceDetailCommand;
import com.beautyservices.bliss.services.domain.model.entities.ServiceDetail;
import com.beautyservices.bliss.services.domain.services.DetailCommandService;
import com.beautyservices.bliss.services.infrastructure.persistence.jpa.repositories.DetailRepository;
import com.beautyservices.bliss.services.infrastructure.persistence.jpa.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailCommandServiceImpl implements DetailCommandService {

    private final DetailRepository detailRepository;
    private final ServiceRepository serviceRepository;

    public DetailCommandServiceImpl(DetailRepository detailRepository, ServiceRepository serviceRepository) {
        this.detailRepository = detailRepository;
        this.serviceRepository = serviceRepository;
    }

    // Create
    @Override
    public Long handle(CreateServiceDetailCommand command) {
        var serviceId = command.serviceId();
        var detailContent = command.detail();

        if (!this.serviceRepository.existsById(serviceId)) {
            throw new IllegalArgumentException("Not possible create Details because service " + serviceId + " does not exist");
        }

        if(this.detailRepository.existsByDetailAndServiceId(detailContent, serviceId)) {
            throw new IllegalArgumentException("Service " + serviceId + " already have this detail " + detailContent);
        }

        var detail = new ServiceDetail(command);
        try {
            this.detailRepository.save(detail);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving service detail: " + e.getMessage());
        }

        return detail.getId();
    }

    // Update
    @Override
    public Optional<ServiceDetail> handle(UpdateServiceDetailCommand command) {
        var detailId = command.serviceDetailId();
        var serviceId = command.serviceId();
        var detail = command.detail();

        if (!this.detailRepository.existsById(detailId)) {
            throw new IllegalArgumentException("Detail with id " + detailId + " does not exist");
        }

        // Validate detail by service id
        if (this.detailRepository.existsByDetailAndServiceIdAndIdIsNot(detail, serviceId, detailId)) {
            throw new IllegalArgumentException("Detail " + detail + " already exists for " + serviceId);
        }

        // Detail to update
        var detailToUpdate = this.detailRepository.findById(detailId).get();

        detailToUpdate.updateServiceDetail(command.detail(), command.price());

        try {
            var updatedDetail = this.detailRepository.save(detailToUpdate);
            return Optional.of(updatedDetail);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating detail: " + e.getMessage());
        }
    }

    // Delete
    @Override
    public void handle(DeleteServiceDetailCommand command) {

        if(!this.detailRepository.existsById(command.serviceDetailId())){
            throw new IllegalArgumentException("Details with id " + command.serviceDetailId() + " does not exist");
        }

        try {
            this.detailRepository.deleteById(command.serviceDetailId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting detail: " + e.getMessage());
        }

    }
}