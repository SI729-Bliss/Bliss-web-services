// ReviewResourceFromEntityAssembler.java
package com.beautyservices.bliss.reviewmanagement.interfaces.rest.transform;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources.ReviewResource;
import org.springframework.stereotype.Component;
import io.swagger.v3.oas.annotations.media.Schema;

@Component
@Schema(description = "Assembler for transforming Review entity to ReviewResource")
public class ReviewResourceFromEntityAssembler {

    public ReviewResource toResource(Review review) {
        return new ReviewResource(
                review.getId(),
                review.getReservationId(),
                review.getPunctuation(),
                review.getComment(),
                review.getReservationInfo(),
                review.getImageUrls()
        );
    }
}