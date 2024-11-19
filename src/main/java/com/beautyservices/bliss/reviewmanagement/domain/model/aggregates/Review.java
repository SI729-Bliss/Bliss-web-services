// Review.java
package com.beautyservices.bliss.reviewmanagement.domain.model.aggregates;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import com.beautyservices.bliss.reviewmanagement.domain.model.valueobjects.ReservationInfo;
import com.beautyservices.bliss.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review extends AuditableAbstractAggregateRoot<Review> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_id", nullable = false)
    private Long reservationId;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "reservation_id", insertable = false, updatable = false)
    private Reservation reservation;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    private int punctuation;
    private String comment;

    @Embedded
    private ReservationInfo reservationInfo;

    @ElementCollection
    @CollectionTable(name = "review_images", joinColumns = @JoinColumn(name = "review_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    public Review() {}

    public Review(Long reservationId, Long userId, int punctuation, String comment, ReservationInfo reservationInfo, List<String> imageUrls) {
        this.reservationId = reservationId;
        this.punctuation = punctuation;
        this.userId = userId;
        this.comment = comment;
        this.reservationInfo = reservationInfo;
        this.imageUrls = imageUrls;
    }
}