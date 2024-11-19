package com.beautyservices.bliss.services.domain.services;

import com.beautyservices.bliss.services.domain.model.aggregates.Service;
import com.beautyservices.bliss.services.domain.model.commands.CreateServiceCommand;
import com.beautyservices.bliss.services.domain.model.commands.DeleteServiceCommand;
import com.beautyservices.bliss.services.domain.model.commands.UpdateServiceCommand;

import java.util.Optional;

public interface EntServiceCommandService {
    Long handle(CreateServiceCommand command);
    Optional<Service> handle(UpdateServiceCommand command);
    void handle(DeleteServiceCommand command);
}