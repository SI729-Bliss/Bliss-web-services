package com.beautyservices.bliss.profilemanagement.domain.model.commands;


public record UpdateCompanyCommand(Long id, String name, String email, String phoneNumber, String address,
                                   double rating){

}