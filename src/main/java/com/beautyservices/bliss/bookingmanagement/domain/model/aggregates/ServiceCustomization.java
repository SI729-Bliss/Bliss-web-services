package com.beautyservices.bliss.bookingmanagement.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "service_customizations")
@Getter
@Setter
public class ServiceCustomization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long serviceId;

    @ElementCollection
    @CollectionTable(name = "customizations", joinColumns = @JoinColumn(name = "service_customization_id"))
    @Column(name = "customization")
    private List<String> customizations;
}