package com.beautyservices.bliss.services.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import com.beautyservices.bliss.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.beautyservices.bliss.services.domain.model.commands.CreateServiceCommand;
import com.beautyservices.bliss.services.domain.model.valueobjects.BeautySalonId;
import com.beautyservices.bliss.services.domain.model.valueobjects.CategoryId;

@Entity
@Table(name = "services")
public class Service extends AuditableAbstractAggregateRoot<Service> {

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "categoryId", column = @Column(name = "category_id")),
    })
    private CategoryId categoryId;

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "image_url", length = 300, nullable = false)
    private String imageUrl;

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "description", length = 300, nullable = false)
    private String description;

    @Getter
    @Min(0)
    @Column(name = "base_price", columnDefinition = "smallint", nullable = false)
    private int basePrice;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "beautySalonId", column = @Column(name = "beauty_salon_id")),
    })
    private BeautySalonId salonId;

    //***************************
    public Service(String name, Long categoryId, String imageUrl, String description, int basePrice, Long salonId) {
        this.name = name;
        this.categoryId = new CategoryId(categoryId);
        this.imageUrl = imageUrl;
        this.description = description;
        this.basePrice = basePrice;
        this.salonId = new BeautySalonId(salonId);
    }

    public Service() {}

    public Service(CategoryId categoryId, BeautySalonId beautySalonId) {
        this();
        this.categoryId = categoryId;
        this.salonId = beautySalonId;
    }
    public Service(Long id) {
        this.id = id;
    }
    public Long getCategoryId(){
        return categoryId.categoryId();
    }

    public Long getSalonId(){
        return salonId.beautySalonId();
    }


    //***************************
    // Command
    public Service(CreateServiceCommand command){
        this.name = command.name();
        this.categoryId = new CategoryId(command.categoryId());
        this.imageUrl = command.imageUrl();
        this.description = command.description();
        this.basePrice = command.basePrice();
        this.salonId = new BeautySalonId(command.salonId());
    }

    // Update
    public Service updateServiceInformation(String name, String imageUrl, String description, int basePrice){
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.basePrice = basePrice;
        return this;
    }

}
