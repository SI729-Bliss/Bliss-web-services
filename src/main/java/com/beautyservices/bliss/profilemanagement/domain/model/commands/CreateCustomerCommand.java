package com.beautyservices.bliss.profilemanagement.domain.model.commands;

public record CreateCustomerCommand (Long id, String name, String email, String phoneNumber, String address){
}
