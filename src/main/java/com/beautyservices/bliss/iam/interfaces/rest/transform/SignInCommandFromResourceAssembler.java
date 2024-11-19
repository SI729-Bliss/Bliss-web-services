package com.beautyservices.bliss.iam.interfaces.rest.transform;


import com.beautyservices.bliss.iam.domain.model.commands.SignInCommand;
import com.beautyservices.bliss.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {

  public static SignInCommand toCommandFromResource(SignInResource signInResource) {
    return new SignInCommand(signInResource.username(), signInResource.password());
  }
}
