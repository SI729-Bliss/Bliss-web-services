package com.beautyservices.bliss.profilemanagement.interfaces.rest.resources;

import com.beautyservices.bliss.profilemanagement.domain.model.valueobjects.Address;
import com.beautyservices.bliss.profilemanagement.domain.model.valueobjects.Email;
import com.beautyservices.bliss.profilemanagement.domain.model.valueobjects.PhoneNumber;

public record UpdateCompanyResource(String name, Email email, Address address, PhoneNumber phoneNumber, double rating) {
}