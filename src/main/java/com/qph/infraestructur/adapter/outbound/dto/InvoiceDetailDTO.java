package com.qph.infraestructur.adapter.outbound.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InvoiceDetailDTO {

    @EqualsAndHashCode.Include
    private Integer idDetail;

    //@NotNull
    @JsonBackReference
    private InvoiceDTO invoice;

    @NotNull
    private Long price;

    @NotNull
    private Integer cant;

    @NotNull
    private Integer idProduct;
}
