package com.beautyservices.bliss.payment.application.internal.eventhandlers;

import com.beautyservices.bliss.payment.domain.model.commands.SeedInvoiceTypesCommand;
import com.beautyservices.bliss.payment.domain.services.InvoiceTypeCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class InvoiceTypeReadyEventHandler {
    private final InvoiceTypeCommandService invoiceTypeCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceTypeReadyEventHandler.class);


    public InvoiceTypeReadyEventHandler(InvoiceTypeCommandService invoiceTypeCommandService) {
        this.invoiceTypeCommandService = invoiceTypeCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event){
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if  Invoice type seeding is needed for {} at {}",
                applicationName, currentTimestamp());

        invoiceTypeCommandService.handle(new SeedInvoiceTypesCommand());
        LOGGER.info("Invoice type seeding verification finished for {} at {}",
                applicationName, currentTimestamp());
    }
    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
