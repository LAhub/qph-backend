package com.qph.infraestructur.adapter.outbound.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SupplierDTO {

    @EqualsAndHashCode.Include
    private Integer idSupplier;

    @NotNull
    private String nameSupplier;

    @NotNull
    private String ruc;
}
