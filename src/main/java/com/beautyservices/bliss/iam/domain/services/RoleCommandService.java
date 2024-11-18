package com.beautyservices.bliss.iam.domain.services;


import com.beautyservices.bliss.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
