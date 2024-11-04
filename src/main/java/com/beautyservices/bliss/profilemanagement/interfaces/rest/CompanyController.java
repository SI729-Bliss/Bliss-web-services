package com.beautyservices.bliss.profilemanagement.interfaces.rest;

import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetCompanyByIdQuery;
import com.beautyservices.bliss.profilemanagement.domain.services.CompanyCommandService;
import com.beautyservices.bliss.profilemanagement.domain.services.CompanyQueryService;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CompanyResource;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.transform.CompanyResourceFromEntityAssembler;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.transform.UpdateCompanyCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT})
@RestController
@RequestMapping(value="/api/v1/companies", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Companies", description = "Company Management Endpoints")
public class CompanyController {
    private final CompanyQueryService companyQueryService;
    private final CompanyCommandService companyCommandService;

    public CompanyController(CompanyQueryService companyQueryService, CompanyCommandService companyCommandService) {
        this.companyQueryService = companyQueryService;
        this.companyCommandService = companyCommandService;
    }

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
