package com.beautyservices.bliss.payment.application.internal.commandservices;

import com.beautyservices.bliss.payment.domain.model.commands.SeedInvoiceTypesCommand;
import com.beautyservices.bliss.payment.domain.model.entities.InvoiceType;
import com.beautyservices.bliss.payment.domain.model.valueobjects.InvoiceTypes;
import com.beautyservices.bliss.payment.domain.services.InvoiceTypeCommandService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.InvoiceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InvoiceTypeCommandServiceImpl implements InvoiceTypeCommandService {

    private final InvoiceTypeRepository invoiceTypeRepository;

    public InvoiceTypeCommandServiceImpl(InvoiceTypeRepository invoiceTypeRepository) {
        this.invoiceTypeRepository = invoiceTypeRepository;
    }

    @Override
    public void handle(SeedInvoiceTypesCommand command) {
        Arrays.stream(InvoiceTypes.values())
                .forEach(invoiceType -> {
                    if(!invoiceTypeRepository.existsByName(invoiceType)) {
                        invoiceTypeRepository.save(new InvoiceType(InvoiceTypes.valueOf(invoiceType.name())));
                    }
                } );
    }
}
