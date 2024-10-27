package com.beautyservices.bliss.profilemanagement.interfaces.rest;

import com.beautyservices.bliss.profilemanagement.domain.model.commands.UpdateCompanyCommand;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.UpdateCustomerCommand;
import com.beautyservices.bliss.profilemanagement.domain.model.queries.GetCustomerByIdQuery;
import com.beautyservices.bliss.profilemanagement.domain.services.CustomerCommandService;
import com.beautyservices.bliss.profilemanagement.domain.services.CustomerQueryService;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CustomerResource;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.transform.CompanyResourceFromEntityAssembler;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.transform.CustomerResourceFromEntityAssembler;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.transform.UpdateCustomerCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.PUT})

@RestController
@RequestMapping(value="/api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Customers", description = "Customer Management Endpoints")
public class CustomerController {

    private final CustomerQueryService customerQueryService;
    private final CustomerCommandService customerCommandService;

    public CustomerController(CustomerQueryService customerQueryService, CustomerCommandService customerCommandService) {
        this.customerQueryService = customerQueryService;
        this.customerCommandService = customerCommandService;
    }


    @GetMapping("/{Customerid}")
    public ResponseEntity<CustomerResource> getCustomerById(@PathVariable Long Customerid) {
        var getCustomerByIdQuery = new GetCustomerByIdQuery(Customerid);
        var optionalCustomer = customerQueryService.handle(getCustomerByIdQuery);
        if (optionalCustomer.isEmpty())
            return ResponseEntity.badRequest().build();
        var customerResource = CustomerResourceFromEntityAssembler.toResourceFromEntity(optionalCustomer.get());
        return ResponseEntity.ok(customerResource);

    }

    @PutMapping("/{Customerid}")
    public ResponseEntity<CustomerResource> updateCustomer(@PathVariable Long id, @RequestBody CustomerResource resource) {
        var updateCustomerCommand = UpdateCustomerCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optionalCustomer = this.customerCommandService.handle(updateCustomerCommand);
        if (optionalCustomer.isEmpty())
            return ResponseEntity.badRequest().build();
        var customerResource = CustomerResourceFromEntityAssembler.toResourceFromEntity(optionalCustomer.get());
        return ResponseEntity.ok(customerResource);
    }


}
