package com.qph.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(InvoiceSupplierPK.class)
public class InvoiceSupplier {

    @Id
    private Invoice invoice;

    @Id
    private Supplier supplier;

}
