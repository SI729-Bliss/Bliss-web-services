package com.beautyservices.bliss.services.domain.model.entities;

import com.beautyservices.bliss.services.domain.model.aggregates.Service;
import com.beautyservices.bliss.services.domain.model.commands.CreateServiceDetailCommand;
import com.beautyservices.bliss.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "service_details")
public class ServiceDetail extends AuditableAbstractAggregateRoot<ServiceDetail> {

    @Getter
    @NotNull
    @Min(0)
    @Column(name = "service_id", columnDefinition = "smallint", nullable = false)
    private Long serviceId;

    @ManyToOne
    @JoinColumn(name = "service", insertable = false, updatable = false)
    private Service service;

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "detail", length = 50, nullable = false)
    private String detail;

    @Getter
    @NotNull
    @Min(0)
    @Column(name = "price", columnDefinition = "smallint", nullable = false)
    private Long price;

    public ServiceDetail(Long serviceId, String detail, Long price) {
        this.serviceId = serviceId;
        this.detail = detail;
        this.price = price;
    }

    public ServiceDetail() {}

    //Command
    public ServiceDetail(CreateServiceDetailCommand command) {
        this.serviceId = command.serviceId();
        this.detail = command.detail();
        this.price = command.price();
    }

    //Update
    public ServiceDetail updateServiceDetail(String detail, Long price){
        this.detail = detail;
        this.price = price;
        return this;
    }

}
