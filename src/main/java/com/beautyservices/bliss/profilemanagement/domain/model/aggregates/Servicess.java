package com.beautyservices.bliss.profilemanagement.domain.model.aggregates;


import com.beautyservices.bliss.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Entity
@Table(name = "services")
public class Servicess extends AuditableAbstractAggregateRoot<Servicess> {

    @NotNull
    @NotBlank
    @Getter
    @Column(name = "name")
    private String name;

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "language")
    private String language;

    @NotNull
    @NotBlank
    @Getter
    @Column(name = "rating")
    private  double rating;

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "service_category")
    private String serviceCategory;

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @NotBlank
    @Getter
    @Column(name = "price")
    private double price;

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "image")
    private String image;


    public Servicess(String name, String language, double rating, String serviceCategory,String description, double price, String image) {
        this.name = name;
        this.language = language;
        this.rating = rating;
        this.serviceCategory = serviceCategory;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Servicess() {}



}

