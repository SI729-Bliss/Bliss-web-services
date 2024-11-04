package com.beautyservices.bliss.reviewmanagement.domain.model.aggregates;

import com.beautyservices.bliss.reviewmanagement.domain.model.valueobjects.Punctuation;
import com.beautyservices.bliss.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "reviews")
@Schema(description = "Entidad representativa de una reseña")
@Getter
@Setter
public class Review extends AuditableAbstractAggregateRoot<Review> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Punctuation punctuation;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private Long reservationId;

    @Column(nullable = false)
    private String images;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}