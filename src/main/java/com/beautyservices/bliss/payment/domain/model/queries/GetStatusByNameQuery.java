package com.beautyservices.bliss.payment.domain.model.queries;

import com.beautyservices.bliss.payment.domain.model.valueobjects.Statuses;

public record GetStatusByNameQuery(Statuses name) {
}
