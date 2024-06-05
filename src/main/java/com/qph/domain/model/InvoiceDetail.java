package com.qph.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idDetail;

    @ManyToOne
    @JoinColumn(name = "id_invoice", nullable = false, foreignKey = @ForeignKey(name = "FK_DETAIL_INVOICE"))
    private Invoice invoice;

    @Column(nullable = false, precision = 10, scale = 2)
    private Long price;

    @Column(nullable = false)
    private Integer cant;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false, foreignKey = @ForeignKey(name = "FK_DETAIL_PRODUCT"))
    private Product product;
}
