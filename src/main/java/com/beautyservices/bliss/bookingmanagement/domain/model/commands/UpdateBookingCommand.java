package com.beautyservices.bliss.bookingmanagement.domain.model.commands;

import com.beautyservices.bliss.bookingmanagement.domain.model.valueobjects.Description;

public record UpdateBookingCommand(Long bookingId, Description message) {
    public UpdateBookingCommand(Long id, Long customerId, Long serviceId, String date, String time, String status, String fullName, String email, String service, String availability, String message, String requirements) {
        this(id, new Description(message));
    }

    public String requirements() {
        return "";
    }

    public Long customerId() {
        return 0L;
    }

    public Long serviceId() {
        return 0L;
    }

    public String date() {
        return "";
    }

    public String time() {
        return "";
    }

    public String status() {
        return "";
    }

    public String fullName() {
        return "";
    }

    public String email() {
        return "";
    }

    public String service() {
        return "";
    }

    public String availability() {
        return "";
    }

    public Object id() {
        return null;
    }
}