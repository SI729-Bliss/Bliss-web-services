package com.beautyservices.bliss.profilemanagement.interfaces.rest;

import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetCompanyByIdQuery;
import com.beautyservices.bliss.profilemanagement.domain.services.CompanyCommandService;
import com.beautyservices.bliss.profilemanagement.domain.services.CompanyQueryService;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CompanyResource;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.transform.CompanyResourceFromEntityAssembler;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.transform.UpdateCompanyCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT})
@RestController
@RequestMapping(value="/api/v1/companies", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles Management", description = "Profiles Management Endpoints")
public class CompanyController {
    private final CompanyQueryService companyQueryService;
    private final CompanyCommandService companyCommandService;

    public CompanyController(CompanyQueryService companyQueryService, CompanyCommandService companyCommandService) {
        this.companyQueryService = companyQueryService;
        this.companyCommandService = companyCommandService;
    }

    @Operation(
            summary = "Fetch companies by ID",
            description = "Fetch companies by ID",
            operationId = "getCompanyById",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CompanyResource.class)
                            )
                    )
            }
    )

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResource> getCompanyById(@PathVariable Long companyId) {
        var getCompanyByIdQuery = new GetCompanyByIdQuery(companyId);
        var optionalCompany = this.companyQueryService.handle(getCompanyByIdQuery);
        if (optionalCompany.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var companyResource = CompanyResourceFromEntityAssembler.toResourceFromEntity(optionalCompany.get());
        return ResponseEntity.ok(companyResource);
    }

    @Operation(
            summary = "Update a company",
            description = "Update the details of an existing company",
            operationId = "updateCompany",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Company updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CompanyResource.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input, object invalid",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Company not found",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyResource> updateCompany(@PathVariable Long companyId, @RequestBody CompanyResource resource) {
        var updateCompanyCommand = UpdateCompanyCommandFromResourceAssembler.toCommandFromResource(companyId, resource);
        var optionalCompany = this.companyCommandService.handle(updateCompanyCommand);
        if (optionalCompany.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var companyResource = CompanyResourceFromEntityAssembler.toResourceFromEntity(optionalCompany.get());
        return ResponseEntity.ok(companyResource);
    }
}