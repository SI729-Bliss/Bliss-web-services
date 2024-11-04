package com.beautyservices.bliss.profilemanagement.interfaces.rest.resources;

public record ServiceResource(double rating, String serviceCategory, String description, String entityDescription,
                              double price, double entityPrice) {
}
