package com.beautyservices.bliss.services.interfaces.rest;

import com.beautyservices.bliss.services.domain.model.valueobjects.BeautySalonId;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.beautyservices.bliss.services.domain.model.commands.DeleteServiceCommand;
import com.beautyservices.bliss.services.domain.model.queries.GetAllServicesQuery;
import com.beautyservices.bliss.services.domain.model.queries.GetServiceByIdQuery;
import com.beautyservices.bliss.services.domain.model.queries.GetServicesBySalonIdQuery;
import com.beautyservices.bliss.services.domain.services.EntServiceCommandService;
import com.beautyservices.bliss.services.domain.services.EntServiceQueryService;
import com.beautyservices.bliss.services.interfaces.rest.resources.CreateServiceResource;
import com.beautyservices.bliss.services.interfaces.rest.resources.ServiceResource;
import com.beautyservices.bliss.services.interfaces.rest.transform.CreateServiceCommandFromResourceAssembler;
import com.beautyservices.bliss.services.interfaces.rest.transform.ServiceResourceFromEntityAssembler;
import com.beautyservices.bliss.services.interfaces.rest.transform.UpdateServiceCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/services", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Services", description = "Service Management Endpoints")
public class ServicesController {

    private final EntServiceQueryService entServiceQueryService;
    private final EntServiceCommandService entServiceCommandService;

    public ServicesController(EntServiceQueryService entServiceQueryService, EntServiceCommandService entServiceCommandService) {
        this.entServiceQueryService = entServiceQueryService;
        this.entServiceCommandService = entServiceCommandService;
    }

    // Create service
    @PostMapping
    public ResponseEntity<ServiceResource> createService(@RequestBody CreateServiceResource resource) {

        var createServiceCommand = CreateServiceCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var serviceId = this.entServiceCommandService.handle(createServiceCommand);

        if (serviceId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getServiceByIdQuery = new GetServiceByIdQuery(serviceId);
        var optionalService = this.entServiceQueryService.handle(getServiceByIdQuery);

        var serviceResource = ServiceResourceFromEntityAssembler.toResourceFromEntity(optionalService.get());
        return new ResponseEntity<>(serviceResource, HttpStatus.CREATED);
    }

    // Get all services
    @GetMapping
    public ResponseEntity<List<ServiceResource>> getAllServices() {
        var getAllServicesQuery = new GetAllServicesQuery();
        var services = this.entServiceQueryService.handle(getAllServicesQuery);
        var servicesResources = services.stream()
                .map(ServiceResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(servicesResources);
    }

    // Get by salon
    @GetMapping("/findBySalon")
    public ResponseEntity<List<ServiceResource>> getBySalonId(@RequestParam(name = "BeautySalonId") Long beautySalonId ) {

        if (beautySalonId == null ) {
            return ResponseEntity.badRequest().build();
        }

        BeautySalonId salonId = new BeautySalonId(beautySalonId);

        var getServicesBySalonIdQuery = new GetServicesBySalonIdQuery(salonId);
        var services = this.entServiceQueryService.handle(getServicesBySalonIdQuery);

        var servicesResources = services.stream()
                .map(ServiceResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(servicesResources);
    }

    // Update Service
    @PutMapping("/{serviceId}")
    public ResponseEntity<ServiceResource> updateService(@PathVariable Long serviceId, @RequestBody ServiceResource resource) {
        var updateServiceCommand = UpdateServiceCommandFromResourceAssembler.toCommandFromResource(serviceId, resource);
        var optionalService = this.entServiceCommandService.handle(updateServiceCommand);

        if(optionalService.isEmpty())
            return ResponseEntity.badRequest().build();
        var serviceResource = ServiceResourceFromEntityAssembler.toResourceFromEntity(optionalService.get());
        return ResponseEntity.ok(serviceResource);
    }

    // Delete Service
    @DeleteMapping("/{serviceId}")
    public ResponseEntity<?> deleteService(@PathVariable Long serviceId) {
        var deleteServiceCommand = new DeleteServiceCommand(serviceId);
        this.entServiceCommandService.handle(deleteServiceCommand);
        return ResponseEntity.ok().build();
    }
}

