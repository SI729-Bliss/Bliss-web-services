package com.beautyservices.bliss.payment.domain.model.queries;

import com.beautyservices.bliss.payment.domain.model.valueobjects.InvoiceTypes;

public record GetInvoiceTypeByNameQuery(InvoiceTypes name) {
}
