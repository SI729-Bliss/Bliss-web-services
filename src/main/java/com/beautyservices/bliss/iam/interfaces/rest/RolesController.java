package com.beautyservices.bliss.iam.interfaces.rest;

import com.beautyservices.bliss.iam.domain.model.queries.GetAllRolesQuery;
import com.beautyservices.bliss.iam.domain.services.RoleQueryService;
import com.beautyservices.bliss.iam.interfaces.rest.resources.RoleResource;
import com.beautyservices.bliss.iam.interfaces.rest.transform.RoleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 *  Roles Controller
 *  This controller is responsible for handling all the requests related to roles
 */
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/ap/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "IAM", description = "Role Management Endpoints")
public class RolesController {

  private final RoleQueryService roleQueryService;

  public RolesController(RoleQueryService roleQueryService) {
    this.roleQueryService = roleQueryService;
  }

  /**
   * Get all roles
   * @return List of role resources
   * @see RoleResource
   */
  @GetMapping
  public ResponseEntity<List<RoleResource>> getAllRoles() {
    var getAllRolesQuery = new GetAllRolesQuery();
    var roles = roleQueryService.handle(getAllRolesQuery);
    var roleResources = roles.stream()
        .map(RoleResourceFromEntityAssembler::toResourceFromEntity)
        .toList();
    return ResponseEntity.ok(roleResources);
  }
}
