package com.beautyservices.bliss.iam.interfaces.rest.transform;


import com.beautyservices.bliss.iam.domain.model.entities.Role;
import com.beautyservices.bliss.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {

  public static RoleResource toResourceFromEntity(Role role) {
    return new RoleResource(role.getId(), role.getStringName());
  }
}
