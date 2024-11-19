package com.beautyservices.bliss.profilemanagement.interfaces.rest.resources;



public record CreateCompanyResource(Long id, String name, String email, String address, String phoneNumber, double rating) {
}
