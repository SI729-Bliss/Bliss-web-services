package com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResource {
    private Long id;
    private int punctuation;
    private String comment;
    private Long reservationId;
    private String images;
    private String createdDate;
}