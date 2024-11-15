package com.beautyservices.bliss.payment.application.internal.commandservices;

import com.beautyservices.bliss.payment.domain.model.commands.SeedStatusesCommand;
import com.beautyservices.bliss.payment.domain.model.entities.InvoiceType;
import com.beautyservices.bliss.payment.domain.model.entities.Status;
import com.beautyservices.bliss.payment.domain.model.valueobjects.InvoiceTypes;
import com.beautyservices.bliss.payment.domain.model.valueobjects.Statuses;
import com.beautyservices.bliss.payment.domain.services.StatusCommandService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StatusCommandServiceImpl implements StatusCommandService {

    private final StatusRepository statusRepository;

    public StatusCommandServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public void handle(SeedStatusesCommand command) {
        Arrays.stream(Statuses.values())
                .forEach(status -> {
                    if(!statusRepository.existsByName(status)) {
                        statusRepository.save(new Status(Statuses.valueOf(status.name())));
                    }
                } );
    }
}