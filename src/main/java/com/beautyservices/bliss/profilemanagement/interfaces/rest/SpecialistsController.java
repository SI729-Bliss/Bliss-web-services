package com.beautyservices.bliss.profilemanagement.interfaces.rest;


import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetAllSpecialistsQuery;
import com.beautyservices.bliss.profilemanagement.domain.services.SpecialistQueryService;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CustomerResource;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.SpecialistResource;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.transform.SpecialistResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/v1/specialists", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles Management", description = "Profiles Management Endpoints")
public class SpecialistsController {
    private final SpecialistQueryService specialistQueryService;

    public SpecialistsController(SpecialistQueryService specialistQueryService) {
        this.specialistQueryService = specialistQueryService;
    }

    @Operation(
            summary = "Fetch all specialists",
            description = "Fetch all specialists from the database",
            operationId = "getAllSpecialists",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SpecialistResource.class)
                            )
                    )
            }
    )
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
