package com.beautyservices.bliss.reviewmanagement.interfaces.rest;

import com.beautyservices.bliss.reviewmanagement.domain.model.queries.GetReviewByReservationIdQuery;
import com.beautyservices.bliss.reviewmanagement.domain.services.ReviewCommandService;
import com.beautyservices.bliss.reviewmanagement.domain.services.ReviewQueryService;
import com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources.CreateReviewResource;
import com.beautyservices.bliss.reviewmanagement.interfaces.rest.resources.ReviewResource;
import com.beautyservices.bliss.reviewmanagement.resources.CreateReviewCommandFromResourceAssembler;
import com.beautyservices.bliss.reviewmanagement.resources.UpdateReviewCommandFromResourceAssembler;
import com.beautyservices.bliss.reviewmanagement.resources.ReviewResourceFromEntityAssembler;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.DeleteReviewCommand;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reviews", description = "Review Management Endpoints")
public class ReviewsController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;
    private final CreateReviewCommandFromResourceAssembler createReviewCommandFromResourceAssembler;
    private final UpdateReviewCommandFromResourceAssembler updateReviewCommandFromResourceAssembler;

    public ReviewsController(ReviewQueryService reviewQueryService,
                             ReviewCommandService reviewCommandService,
                             CreateReviewCommandFromResourceAssembler createReviewCommandFromResourceAssembler,
                             UpdateReviewCommandFromResourceAssembler updateReviewCommandFromResourceAssembler) {
        this.reviewQueryService = reviewQueryService;
        this.reviewCommandService = reviewCommandService;
        this.createReviewCommandFromResourceAssembler = createReviewCommandFromResourceAssembler;
        this.updateReviewCommandFromResourceAssembler = updateReviewCommandFromResourceAssembler;
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<ReviewResource> getReviewByReservationId(@PathVariable Long reservationId) {
        var query = new GetReviewByReservationIdQuery(reservationId);
        var optionalReview = reviewQueryService.handle(query);
        if (!optionalReview.isPresent())
            return ResponseEntity.badRequest().build();
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(optionalReview.get());
        return ResponseEntity.ok(reviewResource);
    }

    @PostMapping
    public ResponseEntity<ReviewResource> createReview(@RequestBody CreateReviewResource resource) {
        var command = createReviewCommandFromResourceAssembler.toCommand(resource);
        var createdReview = reviewCommandService.handle(command);
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(createdReview);
        return ResponseEntity.ok(reviewResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResource> updateReview(@PathVariable Long id, @RequestBody ReviewResource resource) {
        var command = updateReviewCommandFromResourceAssembler.toCommand(id, resource);
        var optionalReview = reviewCommandService.handle(command);
        if (optionalReview.isEmpty())
            return ResponseEntity.badRequest().build();
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(optionalReview.get());
        return ResponseEntity.ok(reviewResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewCommandService.handle(new DeleteReviewCommand(id));
        return ResponseEntity.noContent().build();
    }
}