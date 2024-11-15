package com.beautyservices.bliss.services.interfaces.rest;

import com.beautyservices.bliss.services.domain.model.queries.GetDetailByIdQuery;
import com.beautyservices.bliss.services.domain.model.queries.GetDetailsByServiceIdQuery;
import com.beautyservices.bliss.services.domain.services.DetailCommandService;
import com.beautyservices.bliss.services.domain.services.DetailQueryService;
import com.beautyservices.bliss.services.interfaces.rest.resources.CreateDetailResource;
import com.beautyservices.bliss.services.interfaces.rest.resources.DetailResource;
import com.beautyservices.bliss.services.interfaces.rest.transform.CreateDetailCommandFromResourceAssembler;
import com.beautyservices.bliss.services.interfaces.rest.transform.DetailResourceFromEntityAssembler;
import com.beautyservices.bliss.services.interfaces.rest.transform.UpdateDetailCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/services/details", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Services", description = "Service Details Management Endpoints")
public class DetailsController {

    private final DetailQueryService detailQueryService;
    private final DetailCommandService detailCommandService;

    public DetailsController(DetailQueryService detailQueryService, DetailCommandService detailCommandService){
        this.detailQueryService = detailQueryService;
        this.detailCommandService = detailCommandService;
    }

    // Create detail
    @Operation(
            summary = "Add a new service detail",
            description = "Add a new service detail for service",
            operationId = "createDetail",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateDetailResource.class)
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
    public ResponseEntity<DetailResource> createDetail(@RequestBody CreateDetailResource resource){
        var createDetailCommand = CreateDetailCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var detailId = this.detailCommandService.handle(createDetailCommand);

        if (detailId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getDetailByIdQuery = new GetDetailByIdQuery(detailId);
        var optionalDetail = this.detailQueryService.handle(getDetailByIdQuery);

        var detailResource = DetailResourceFromEntityAssembler.toResourceFromEntity(optionalDetail.get());
        return new ResponseEntity<>(detailResource, HttpStatus.CREATED);
    }

    // Get by Service id
    @Operation(
            summary = "Fetch Details by Service id",
            description = "Fetch all Details by Service id",
            operationId = "getDetailsByServiceId",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DetailResource.class)
                            )
                    )
            }
    )
    @GetMapping("/findByService")
    public ResponseEntity<List<DetailResource>> getByService(@RequestParam(name = "serviceId") Long serviceId) {
        if (serviceId == null) {
            return ResponseEntity.badRequest().build();
        }

        var getDetailsByServiceIdQuery = new GetDetailsByServiceIdQuery(serviceId);
        var details = this.detailQueryService.handle(getDetailsByServiceIdQuery);

        var detailsResources = details.stream()
                .map(DetailResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(detailsResources);
    }

    // Update
    @Operation(
            summary = "Update a detail",
            description = "Update details for one service",
            operationId = "updateDetail",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful update",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateDetailResource.class)
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
    @PutMapping("/{detailId}")
    public ResponseEntity<DetailResource> updateDetail(@PathVariable Long detailId, @RequestBody DetailResource resource){
        var updateDetailCommand = UpdateDetailCommandFromResourceAssembler.toCommandFromResource(detailId, resource);
        var optionalDetail = this.detailCommandService.handle(updateDetailCommand);

        if(optionalDetail.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var detailResource = DetailResourceFromEntityAssembler.toResourceFromEntity(optionalDetail.get());
        return ResponseEntity.ok(detailResource);
    }
}
