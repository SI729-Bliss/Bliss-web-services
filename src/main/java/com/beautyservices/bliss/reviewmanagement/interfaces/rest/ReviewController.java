package com.beautyservices.bliss.reviewmanagement.interfaces.rest;

import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.interfaces.acl.ReviewFacade;
import com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources.CreateReviewResource;
import com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources.ReviewResource;
import com.beautyservices.bliss.reviewmanagement.interfaces.rest.transform.CreateReviewCommandFromResourceAssembler;
import com.beautyservices.bliss.reviewmanagement.interfaces.rest.transform.ReviewResourceFromEntityAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewFacade reviewFacade;
    private final CreateReviewCommandFromResourceAssembler createReviewCommandFromResourceAssembler;
    private final ReviewResourceFromEntityAssembler reviewResourceFromEntityAssembler;

    @Autowired
    public ReviewController(ReviewFacade reviewFacade,
                            CreateReviewCommandFromResourceAssembler createReviewCommandFromResourceAssembler,
                            ReviewResourceFromEntityAssembler reviewResourceFromEntityAssembler) {
        this.reviewFacade = reviewFacade;
        this.createReviewCommandFromResourceAssembler = createReviewCommandFromResourceAssembler;
        this.reviewResourceFromEntityAssembler = reviewResourceFromEntityAssembler;
    }

    @PostMapping
    public ResponseEntity<ReviewResource> createReview(@RequestBody CreateReviewResource createReviewResource) {
        var command = createReviewCommandFromResourceAssembler.toCommand(createReviewResource);
        Optional<Review> reviewOpt = reviewFacade.createReview(command);
        return reviewOpt.map(review -> ResponseEntity.ok(reviewResourceFromEntityAssembler.toResource(review)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResource> updateReview(@PathVariable Long id, @RequestBody Review review) {
        Optional<Review> reviewOpt = reviewFacade.updateReview(id, review.getPunctuation(), review.getComment());
        return reviewOpt.map(updatedReview -> ResponseEntity.ok(reviewResourceFromEntityAssembler.toResource(updatedReview)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewFacade.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResource> getReviewById(@PathVariable Long id) {
        Optional<Review> reviewOpt = reviewFacade.getReviewById(id);
        return reviewOpt.map(review -> ResponseEntity.ok(reviewResourceFromEntityAssembler.toResource(review)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<ReviewResource>> getReviewsByCompanyId(@PathVariable Long companyId) {
        List<Review> reviews = reviewFacade.getReviewsByCompanyId(companyId);
        List<ReviewResource> reviewResources = reviews.stream()
                .map(reviewResourceFromEntityAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewResources);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResource>> getReviewsByUserId(@PathVariable Long userId) {
        List<Review> reviews = reviewFacade.getReviewsByUserId(userId);
        List<ReviewResource> reviewResources = reviews.stream()
                .map(reviewResourceFromEntityAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewResources);
    }
}