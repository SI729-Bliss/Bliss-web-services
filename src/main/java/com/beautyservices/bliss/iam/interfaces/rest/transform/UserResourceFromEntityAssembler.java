package com.beautyservices.bliss.iam.interfaces.rest.transform;


import com.beautyservices.bliss.iam.domain.model.aggregates.User;
import com.beautyservices.bliss.iam.interfaces.rest.resources.UserResource;
import com.beautyservices.bliss.iam.domain.model.entities.Role;



public class UserResourceFromEntityAssembler {

  public static UserResource toResourceFromEntity(User user) {
    var roles = user.getRoles().stream()
        .map(Role::getStringName)
        .toList();
    return new UserResource(user.getId(), user.getUsername(), roles);
  }
}
