package com.beautyservices.bliss.reviewmanagement.resources;

import com.beautyservices.bliss.reviewmanagement.domain.model.commands.UpdateReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.model.valueobjects.Punctuation;
import com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources.ReviewResource;
import org.springframework.stereotype.Component;

@Component
public class UpdateReviewCommandFromResourceAssembler {
    public static UpdateReviewCommand toCommand(Long id, ReviewResource resource) {
        return new UpdateReviewCommand(
                id,
                new Punctuation(resource.getPunctuation()),
                resource.getComment(),
                resource.getImages()
        );
    }
}