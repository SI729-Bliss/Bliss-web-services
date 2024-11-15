package com.beautyservices.bliss.payment.domain.services;

import com.beautyservices.bliss.payment.domain.model.entities.InvoiceType;
import com.beautyservices.bliss.payment.domain.model.queries.GetInvoiceTypeByNameQuery;

import java.util.Optional;

public interface InvoiceTypeQueryService {
    Optional<InvoiceType> handle(GetInvoiceTypeByNameQuery query);
}
