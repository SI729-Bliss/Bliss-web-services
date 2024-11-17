
package com.beautyservices.bliss.reviewmanagement.interfaces.rest.transform;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources.ReviewResource;
import org.springframework.stereotype.Component;

@Component
public class ReviewResourceFromEntityAssembler {

    public ReviewResource toResource(Review review) {
        return new ReviewResource(
                review.getId(),
                review.getReservation().getId(),
                review.getPunctuation(),
                review.getComment(),
                review.getReservationInfo()
        );
    }
}