package com.beautyservices.bliss.services.interfaces.rest;

import com.beautyservices.bliss.services.domain.model.valueobjects.BeautySalonId;
import com.beautyservices.bliss.services.interfaces.rest.resources.CreateDetailResource;
import com.beautyservices.bliss.services.interfaces.rest.resources.DetailResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Add a new service",
            description = "Add a new service by Beauty Salon",
            operationId = "createService",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateServiceResource.class)
                            )
                    ),
                    @ApiResponse (
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content (
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    )
            }
    )
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
    @Operation(
            summary = "Fetch all services",
            description = "Fetch all services created",
            operationId = "getServices",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ServiceResource.class)
                            )
                    )
            }
    )
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
    @Operation(
            summary = "Fetch all services for one salon",
            description = "Fetch all services by beauty salon id",
            operationId = "getServicesBySalonId",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ServiceResource.class)
                            )
                    )
            }
    )
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
    @Operation(
            summary = "Update a service",
            description = "Update service by service id",
            operationId = "updateService",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful update",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DetailResource.class)
                            )
                    ),
                    @ApiResponse (
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content (
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    )
            }
    )
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
    @Operation(
            summary = "Delete a service",
            description = "Delete a service by service id",
            operationId = "deleteService",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful delete",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ServiceResource.class)
                            )
                    )
            }
    )
    @DeleteMapping("/{serviceId}")
    public ResponseEntity<?> deleteService(@PathVariable Long serviceId) {
        var deleteServiceCommand = new DeleteServiceCommand(serviceId);
        this.entServiceCommandService.handle(deleteServiceCommand);
        return ResponseEntity.ok().build();
    }
}

