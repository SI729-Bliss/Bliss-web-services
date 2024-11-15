package com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReviewResource {
    private int punctuation;
    private String comment;
    private Long reservationId;
    private String images;
}