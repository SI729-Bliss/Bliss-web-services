package com.beautyservices.bliss.profilemanagement.domain.model.aggregates;

import com.beautyservices.bliss.profilemanagement.domain.model.valueobjects.Email;
import com.beautyservices.bliss.profilemanagement.domain.model.valueobjects.PhoneNumber;
import com.beautyservices.bliss.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Entity
@Table(name = "specialists")
public class Specialist extends AuditableAbstractAggregateRoot<Specialist> {


    @Getter
    @NotNull
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Getter
    @NotBlank
    @Column(name = "specialism", nullable = false)
    private String specialism;

    @NotNull
    @Getter
    @NotBlank
    @Column(name = "image", nullable = false)
    private String image;

    @NotNull
    @Getter
    @NotBlank
    @Column(name = "rating", nullable = false)
    private double rating;


    public Specialist(String specialism, String image,double rating) {
        this.specialism = specialism;
        this.image = image;
        this.rating = rating;
    }

    public Specialist() {}

}