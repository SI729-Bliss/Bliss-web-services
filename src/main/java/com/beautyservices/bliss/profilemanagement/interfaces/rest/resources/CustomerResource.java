package com.beautyservices.bliss.profilemanagement.interfaces.rest.resources;


public record CustomerResource(Long id, String name, String email, String phoneNumber, String address) { }