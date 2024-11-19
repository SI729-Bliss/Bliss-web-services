package com.beautyservices.bliss.services.domain.services;

import com.beautyservices.bliss.services.domain.model.commands.CreateServiceDetailCommand;
import com.beautyservices.bliss.services.domain.model.commands.DeleteServiceDetailCommand;
import com.beautyservices.bliss.services.domain.model.commands.UpdateServiceDetailCommand;
import com.beautyservices.bliss.services.domain.model.entities.ServiceDetail;

import java.util.Optional;

public interface DetailCommandService {
    Long handle(CreateServiceDetailCommand command);
    Optional<ServiceDetail> handle(UpdateServiceDetailCommand command);
    void handle(DeleteServiceDetailCommand command);
}
