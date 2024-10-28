/*package com.beautyservices.bliss.payment.domain.model.aggregates;

import com.beautyservices.bliss.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Customer extends AuditableAbstractAggregateRoot<Customer> {

        @NotNull
        private String name;
        @NotNull
        private String email;
        @NotNull
        private String phone;

        public Customer() {
        }

        public Customer(String name, String email, String phone) {
            this();
            this.name = name;
            this.email = email;
            this.phone = phone;
        }
}
*/