package com.beautyservices.bliss.payment.domain.model.entities;

import com.beautyservices.bliss.payment.domain.model.valueobjects.InvoiceTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(name = "invoice_types")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class InvoiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private InvoiceTypes name;

    public InvoiceType(InvoiceTypes name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static InvoiceType getDefaultInvoiceType() {
        return new InvoiceType(InvoiceTypes.BOLETA);
    }

    public static InvoiceType toInvoiceTypeFromName(String name) {
        return new InvoiceType(InvoiceTypes.valueOf(name));
    }
}
