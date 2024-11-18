package com.beautyservices.bliss.profilemanagement.domain.model.commands;

public record UpdateCustomerCommand(Long id, String name, String email, String phoneNumber, String address){}