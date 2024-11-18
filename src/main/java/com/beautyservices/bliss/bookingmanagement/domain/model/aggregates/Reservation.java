package com.beautyservices.bliss.bookingmanagement.domain.model.aggregates;

import com.beautyservices.bliss.bookingmanagement.domain.model.commands.CreateBookingCommand;
import com.beautyservices.bliss.bookingmanagement.domain.model.commands.UpdateBookingCommand;
import com.beautyservices.bliss.profilemanagement.domain.model.aggregates.Company;
import com.beautyservices.bliss.services.domain.model.aggregates.Service;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long customerId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @NotNull
    private String bookingDate;

    @NotNull
    private String bookingTime;

    @NotNull
    private boolean bookingStatus;

    @ElementCollection
    private List<String> requirements;

    // Constructor for CreateBookingCommand
    public Reservation(CreateBookingCommand command) {
        this.customerId = command.customerId();
        this.service = new Service(command.serviceId());
        this.company = new Company(command.companyId());
        this.bookingDate = command.bookingDate();
        this.bookingTime = command.bookingTime();
        this.bookingStatus = command.bookingStatus();
        this.requirements = command.requirements();
    }

    // Default constructor
    public Reservation() {}

    // Update method for UpdateBookingCommand
    public void update(UpdateBookingCommand command) {
        this.bookingStatus = command.bookingStatus();
        this.requirements = command.requirements();
    }

    // New methods to get service and company IDs
    public Long getServiceId() {
        return service.getId();
    }

    public Long getCompanyId() {
        return company.getId();
    }
}