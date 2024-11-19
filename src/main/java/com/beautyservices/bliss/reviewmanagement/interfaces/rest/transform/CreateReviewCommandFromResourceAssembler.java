package com.beautyservices.bliss.reviewmanagement.interfaces.rest.transform;

import com.beautyservices.bliss.reviewmanagement.domain.model.commands.CreateReviewCommand;
import com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources.CreateReviewResource;
import org.springframework.stereotype.Component;

@Component
public class CreateReviewCommandFromResourceAssembler {

    public CreateReviewCommand toCommand(CreateReviewResource resource) {
        return new CreateReviewCommand(
                resource.reservationId(),
                resource.userId(),
                resource.punctuation(),
                resource.comment(),
                resource.imageUrls()
        );
    }
}