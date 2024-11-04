package com.beautyservices.bliss.reviewmanagement.resources;

import com.beautyservices.bliss.reviewmanagement.domain.model.commands.CreateReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.model.valueobjects.Punctuation;
import com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources.CreateReviewResource;
import org.springframework.stereotype.Component;

@Component
public class CreateReviewCommandFromResourceAssembler {
    public CreateReviewCommand toCommand(CreateReviewResource resource) {
        return new CreateReviewCommand(
                new Punctuation(resource.getPunctuation()),
                resource.getComment(),
                resource.getReservationId(),
                resource.getImages()
        );
    }
}