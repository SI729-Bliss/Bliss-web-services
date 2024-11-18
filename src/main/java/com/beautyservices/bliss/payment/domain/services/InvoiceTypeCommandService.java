package com.beautyservices.bliss.payment.domain.services;

import com.beautyservices.bliss.payment.domain.model.commands.SeedInvoiceTypesCommand;

public interface InvoiceTypeCommandService {
    void handle(SeedInvoiceTypesCommand command);
}
