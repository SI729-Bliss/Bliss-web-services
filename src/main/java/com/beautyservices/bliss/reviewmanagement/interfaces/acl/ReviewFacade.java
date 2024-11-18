package com.beautyservices.bliss.reviewmanagement.interfaces.acl;

import com.beautyservices.bliss.profilemanagement.application.internal.outboundservices.acl.CompanyRatingService;
import com.beautyservices.bliss.reviewmanagement.domain.model.aggregates.Review;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.CreateReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.UpdateReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.model.commands.DeleteReviewCommand;
import com.beautyservices.bliss.reviewmanagement.domain.model.valueobjects.ReservationInfo;
import com.beautyservices.bliss.reviewmanagement.domain.services.ReviewCommandService;
import com.beautyservices.bliss.reviewmanagement.infrastructure.persistence.jpa.repositories.ExternalReservationRepository;
import com.beautyservices.bliss.reviewmanagement.infrastructure.persistence.jpa.repositories.ReviewRepository;
import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewFacade {

    private final ReviewCommandService reviewCommandService;
    private final CompanyRatingService companyRatingService;
    private final ExternalReservationRepository externalReservationRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewFacade(ReviewCommandService reviewCommandService, CompanyRatingService companyRatingService,
                        ExternalReservationRepository externalReservationRepository, ReviewRepository reviewRepository) {
        this.reviewCommandService = reviewCommandService;
        this.companyRatingService = companyRatingService;
        this.externalReservationRepository = externalReservationRepository;
        this.reviewRepository = reviewRepository;
    }

    public Optional<Review> createReview(CreateReviewCommand command) {
        Optional<Reservation> reservationOpt = externalReservationRepository.findById(command.reservationId());
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            ReservationInfo reservationInfo = new ReservationInfo(reservation.getServiceId(), reservation.getCompanyId());
            Review review = new Review(reservation.getId(), command.punctuation(), command.comment(), reservationInfo, command.imageUrls());            Review savedReview = reviewCommandService.createReview(command).orElse(null);
            if (savedReview != null) {
                companyRatingService.updateCompanyRating(reservation.getCompanyId(), command.punctuation());
                return Optional.of(savedReview);
            }
        }
        return Optional.empty();
    }

    public Optional<Review> updateReview(Long id, int punctuation, String comment, List<String> imageUrls) {
        return reviewCommandService.updateReview(new UpdateReviewCommand(id, punctuation, comment, imageUrls));
    }

    public void deleteReview(Long id) {
        reviewCommandService.deleteReview(new DeleteReviewCommand(id));
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> getReviewsByCompanyId(Long companyId) {
        return reviewRepository.findByReservationInfoCompanyId(companyId);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByReservationId(userId);
    }


    public List<Review> getReviewsByReservationId(Long reservationId) {
        return reviewRepository.findByReservationId(reservationId);
    }
}