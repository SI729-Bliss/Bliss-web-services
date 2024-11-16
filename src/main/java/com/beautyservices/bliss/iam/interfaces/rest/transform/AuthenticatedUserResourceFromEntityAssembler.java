package com.beautyservices.bliss.iam.interfaces.rest.transform;

import com.beautyservices.bliss.iam.domain.model.aggregates.User;
import com.beautyservices.bliss.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {

  public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
    return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
  }
}
