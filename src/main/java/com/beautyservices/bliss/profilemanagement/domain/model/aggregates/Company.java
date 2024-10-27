package com.beautyservices.bliss.profilemanagement.domain.model.aggregates;

import com.beautyservices.bliss.profilemanagement.domain.model.valueobjects.Address;
import com.beautyservices.bliss.profilemanagement.domain.model.valueobjects.Email;
import com.beautyservices.bliss.profilemanagement.domain.model.valueobjects.PhoneNumber;
import com.beautyservices.bliss.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Entity
@Table(name = "companies")
@Schema(description = "Entidad que representa a un salon de belleza")
public class Company extends AuditableAbstractAggregateRoot<Company> {

    @Getter
    @Column(nullable = false, length = 100)
    private String name;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "email", column = @Column(name = "email"))
    })
    private Email email;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "phone_number", nullable = false))
    })
    private PhoneNumber phoneNumber;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "address", column = @Column(name = "address", nullable = false))
    })
    private Address address;

    //getters
    public String getEmail() {
        return email.email();
    }

    public String getAddress() {
        return address.street();
    }

    public String getPhoneNumber() {
        return phoneNumber.number();
    }


    // Constructor vacío requerido por JPA
    public Company() {}

    public Company(String name, String email, String phoneNumber, String address) {
        this.name = name;
        this.email = new Email(email);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.address = new Address(address);
    }
    public Company updateInformation(String name, String email, String phoneNumber, String address) {
        this.name = name;
        this.email = new Email(email);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.address = new Address(address);
        return this;

    }
}

