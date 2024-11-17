// Review.java
package com.beautyservices.bliss.reviewmanagement.domain.model.aggregates;

import com.beautyservices.bliss.bookingmanagement.domain.model.aggregates.Reservation;
import com.beautyservices.bliss.reviewmanagement.domain.model.valueobjects.ReservationInfo;
import com.beautyservices.bliss.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review extends AuditableAbstractAggregateRoot<Review> {



    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id", nullable = false)
    private Reservation reservation;

    private int punctuation;
    private String comment;

    @Embedded
    private ReservationInfo reservationInfo;


    public Review() {}


    public Review(Reservation reservation, int punctuation, String comment, ReservationInfo reservationInfo) {
        this.reservation = reservation;
        this.punctuation = punctuation;
        this.comment = comment;
        this.reservationInfo = reservationInfo;
    }
}