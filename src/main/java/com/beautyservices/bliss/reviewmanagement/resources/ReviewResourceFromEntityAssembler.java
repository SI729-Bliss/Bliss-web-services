package com.beautyservices.bliss.reviewmanagement.resources;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {
    public static ReviewResource toResourceFromEntity(Review review) {
        ReviewResource resource = new ReviewResource();
        resource.setId(review.getId());
        resource.setPunctuation(review.getPunctuation().value());
        resource.setComment(review.getComment());
        resource.setReservationId(review.getReservationId());
        resource.setImages(review.getImages());
        resource.setCreatedDate(review.getCreatedDate().toString());
        return resource;
    }
}