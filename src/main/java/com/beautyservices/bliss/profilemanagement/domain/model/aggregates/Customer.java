package com.beautyservices.bliss.profilemanagement.domain.model.aggregates;

import com.beautyservices.bliss.profilemanagement.domain.model.valueobjects.Address;
import com.beautyservices.bliss.profilemanagement.domain.model.valueobjects.Email;
import com.beautyservices.bliss.profilemanagement.domain.model.valueobjects.PhoneNumber;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "customers")
public class Customer extends Company {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false, length = 100)
    private String name;

    @Setter
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "email", column = @Column(name = "email", nullable = false))
    })
    private Email email;

    @Setter
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "phoneNumber", nullable = false))
    })
    private PhoneNumber phoneNumber;

    @Setter
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "address", column = @Column(name = "address", nullable = false))
    })
    private Address address;

    //getters
    public String getEmail() {
        return email.value();
    }

    public String getAddress() {
        return address.street();
    }

    public String getPhoneNumber() {
        return phoneNumber.value();
    }

    // Constructor vacío requerido por JPA
    public Customer() {}

    public Customer(String name, String email, String phoneNumber, String address) {
        this.name = name;
        this.email = new Email(email);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.address = new Address();
    }
    public Customer updateInformation(String name, String email, String phoneNumber, String address) {
        this.name = name;
        this.email = new Email(email);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.address = new Address(address);
        return this;
    }
}
