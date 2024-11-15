package com.beautyservices.bliss.profilemanagement.interfaces.rest;

import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetAllServicesQuery;
import com.beautyservices.bliss.profilemanagement.domain.services.ServicesQueryService;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.ServiceResource;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.transform.ServiceResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/v1/services", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Services", description = "Services Management Endpoints")
public class ServicesController {
    private final ServicesQueryService servicesQueryService;

    public ServicesController(ServicesQueryService servicesQueryService) {
        this.servicesQueryService = servicesQueryService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceResource>> getAllServices() {
        var getAllServicesQuery = new GetAllServicesQuery();
        var services = this.servicesQueryService.handle(getAllServicesQuery);
        var serviceResources = services.stream()
                .map(ServiceResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(serviceResources, HttpStatus.OK);
    }
}
