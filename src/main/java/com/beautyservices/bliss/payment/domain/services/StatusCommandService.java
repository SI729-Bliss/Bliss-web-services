package com.beautyservices.bliss.payment.domain.services;

import com.beautyservices.bliss.payment.domain.model.commands.SeedStatusesCommand;

public interface StatusCommandService {
    void handle(SeedStatusesCommand command);
}
