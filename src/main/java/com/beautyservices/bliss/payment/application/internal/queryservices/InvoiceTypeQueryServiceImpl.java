package com.beautyservices.bliss.payment.application.internal.queryservices;

import com.beautyservices.bliss.payment.domain.model.entities.InvoiceType;
import com.beautyservices.bliss.payment.domain.model.queries.GetInvoiceTypeByNameQuery;
import com.beautyservices.bliss.payment.domain.services.InvoiceTypeQueryService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.InvoiceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceTypeQueryServiceImpl implements InvoiceTypeQueryService {

    private final InvoiceTypeRepository invoiceTypeRepository;

    public InvoiceTypeQueryServiceImpl(InvoiceTypeRepository invoiceTypeRepository) {
        this.invoiceTypeRepository = invoiceTypeRepository;
    }

    @Override
    public Optional<InvoiceType> handle(GetInvoiceTypeByNameQuery query) {
        return this.invoiceTypeRepository.findByName(query.name());
    }
}
