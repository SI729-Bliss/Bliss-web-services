package com.beautyservices.bliss.payment.application.internal.eventhandlers;


import com.beautyservices.bliss.payment.domain.model.commands.SeedStatusesCommand;
import com.beautyservices.bliss.payment.domain.services.StatusCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class StatusReadyEventHandler {
    private final StatusCommandService statusCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusReadyEventHandler.class);

    public StatusReadyEventHandler(StatusCommandService statusCommandService) {
        this.statusCommandService = statusCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event){
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if  statuses seeding is needed for {} at {}",
                applicationName, currentTimestamp());

        statusCommandService.handle(new SeedStatusesCommand());
        LOGGER.info("Statuses seeding verification finished for {} at {}",
                applicationName, currentTimestamp());
    }
    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
