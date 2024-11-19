package com.beautyservices.bliss.payment.application.internal.eventhandlers;


import com.beautyservices.bliss.payment.domain.model.commands.SeedPaymentMethodsCommand;
import com.beautyservices.bliss.payment.domain.services.PaymentMethodCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class PaymentMethodReadyEventHandler {
    private final PaymentMethodCommandService paymentMethodCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentMethodReadyEventHandler.class);

    public PaymentMethodReadyEventHandler(PaymentMethodCommandService paymentMethodCommandService) {
         this.paymentMethodCommandService = paymentMethodCommandService;
     }

    @EventListener
    public void on(ApplicationReadyEvent event){
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if  payment methods seeding is needed for {} at {}",
                applicationName, currentTimestamp());

         paymentMethodCommandService.handle(new SeedPaymentMethodsCommand());
         LOGGER.info("Payment methods seeding verification finished for {} at {}",
                 applicationName, currentTimestamp());
     }
     private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
     }
}
