package com.beautyservices.bliss.bookingmanagement.interfaces.rest;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.ServiceCustomization;
import com.beautyservices.bliss.bookingmanagement.domain.services.ServiceCustomizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service_customizations")
public class ServiceCustomizationController {
    private final ServiceCustomizationService serviceCustomizationService;

    public ServiceCustomizationController(ServiceCustomizationService serviceCustomizationService) {
        this.serviceCustomizationService = serviceCustomizationService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceCustomization>> getAllServiceCustomizations() {
        return ResponseEntity.ok(serviceCustomizationService.getAllServiceCustomizations());
    }

    @PostMapping
    public ResponseEntity<ServiceCustomization> createServiceCustomization(@RequestBody ServiceCustomization serviceCustomization) {
        return ResponseEntity.ok(serviceCustomizationService.saveServiceCustomization(serviceCustomization));
    }
}