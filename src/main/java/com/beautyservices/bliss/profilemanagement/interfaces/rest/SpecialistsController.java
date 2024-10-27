package com.beautyservices.bliss.profilemanagement.interfaces.rest;


import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetAllSpecialistsQuery;
import com.beautyservices.bliss.profilemanagement.domain.services.SpecialistQueryService;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.SpecialistResource;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.transform.SpecialistResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/v1/specialists", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Specialists", description = "Specialists Management Endpoints")
public class SpecialistsController {
    private final SpecialistQueryService specialistQueryService;

    public SpecialistsController(SpecialistQueryService specialistQueryService) {
        this.specialistQueryService = specialistQueryService;
    }

    @GetMapping
    public ResponseEntity<List<SpecialistResource>> getAllSpecialists() {
        var getAllSpecialistsQuery = new GetAllSpecialistsQuery();
        var specialists = this.specialistQueryService.handle(getAllSpecialistsQuery);
        var specialistResources = specialists.stream()
                .map(SpecialistResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(specialistResources);
    }
}
