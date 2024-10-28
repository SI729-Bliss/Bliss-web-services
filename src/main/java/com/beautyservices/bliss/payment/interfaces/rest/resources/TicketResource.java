package com.beautyservices.bliss.payment.interfaces.rest.resources;

import java.time.LocalDate;

public record TicketResource(Long id, LocalDate date, Long reservationId) {
}
