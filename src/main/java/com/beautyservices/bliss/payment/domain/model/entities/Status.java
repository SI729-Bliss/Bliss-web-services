package com.beautyservices.bliss.payment.domain.model.entities;

import com.beautyservices.bliss.payment.domain.model.valueobjects.Statuses;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(name = "statusesnashe")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private Statuses name;

    public Status(Statuses name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static Status getDefaultStatus() {
        return new Status(Statuses.PENDING);
    }

    public static Status toStatusFromName(String name) {
        return new Status(Statuses.valueOf(name));
    }
}
