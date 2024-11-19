package com.beautyservices.bliss.profilemanagement.domain.model.commands;

public record CreateCompanyCommand(Long id, String name, String email, String phoneNumber, String address,
                                   double rating) {
}
