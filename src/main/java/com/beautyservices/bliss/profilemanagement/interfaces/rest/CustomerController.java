package com.beautyservices.bliss.profilemanagement.interfaces.rest;

import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetAllCustomersQuery;
import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetCompanyByIdQuery;
import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetCustomerByIdQuery;
import com.beautyservices.bliss.profilemanagement.domain.services.CustomerCommandService;
import com.beautyservices.bliss.profilemanagement.domain.services.CustomerQueryService;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CompanyResource;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CreateCustomerResource;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CustomerResource;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.transform.CreateCompanyCommandFromResourceAssembler;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.transform.CreateCustomerCommandFromResourceAssembler;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.transform.CustomerResourceFromEntityAssembler;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.transform.UpdateCustomerCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@RestController
@RequestMapping(value="/api/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles Management", description = "Profiles Management Endpoints")
public class CustomerController {

    private final CustomerQueryService customerQueryService;
    private final CustomerCommandService customerCommandService;

    public CustomerController(CustomerQueryService customerQueryService, CustomerCommandService customerCommandService) {
        this.customerQueryService = customerQueryService;
        this.customerCommandService = customerCommandService;
    }


    @Operation(
            summary = "Fetch customers by ID",
            description = "Fetch customers by ID",
            operationId = "getCustomerById",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerResource.class)
                            )
                    )
            }
    )
    @GetMapping("/{customerid}")
    public ResponseEntity<CustomerResource> getCustomerById(@PathVariable Long customerid) {
        var getCustomerByIdQuery = new GetCustomerByIdQuery(customerid);
        var optionalCustomer = customerQueryService.handle(getCustomerByIdQuery);
        if (optionalCustomer.isEmpty())
            return ResponseEntity.badRequest().build();
        var customerResource = CustomerResourceFromEntityAssembler.toResourceFromEntity(optionalCustomer.get());
        return ResponseEntity.ok(customerResource);

    }

    @Operation(
            summary = "Update a customer",
            description = "Update the details of an existing customer",
            operationId = "updateCustomer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "customer updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerResource.class)
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
                            description = "Customer not found",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResource> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerResource resource) {
        var updateCustomerCommand = UpdateCustomerCommandFromResourceAssembler.toCommandFromResource(customerId, resource);
        var optionalCustomer = this.customerCommandService.handle(updateCustomerCommand);
        if (optionalCustomer.isEmpty())
            return ResponseEntity.badRequest().build();
        var customerResource = CustomerResourceFromEntityAssembler.toResourceFromEntity(optionalCustomer.get());
        return ResponseEntity.ok(customerResource);
    }

    @Operation(
            summary = "Create a customer",
            description = "Create a new customer",
            operationId = "createCustomer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "customer created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerResource.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input, object invalid",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @PostMapping
   public ResponseEntity<CustomerResource> createCustomer(@RequestBody CreateCustomerResource resource) {
       var createCustomerCommand = CreateCustomerCommandFromResourceAssembler.toCommand(resource);
       var customerId = this.customerCommandService.handle(createCustomerCommand);
       if (customerId.equals(0L)) {
           return ResponseEntity.badRequest().build();
       }
       var getCustomerByIdQuery = new GetCustomerByIdQuery(customerId);
       var optionalCustomer = this.customerQueryService.handle(getCustomerByIdQuery);
       if (optionalCustomer.isEmpty()) {
           return ResponseEntity.badRequest().build();
       }
       var customerResource = CustomerResourceFromEntityAssembler.toResourceFromEntity(optionalCustomer.get());
       return ResponseEntity.ok(customerResource);
   }

    @Operation(
            summary = "Fetch all customers",
            description = "Fetch all customers",
            operationId = "getAllCustomers",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerResource.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<CustomerResource>> getAllCustomers() {
         var customers = this.customerQueryService.handle(new GetAllCustomersQuery());
         var customerResources = customers.stream()
                .map(CustomerResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
         return ResponseEntity.ok(customerResources);
    }

}