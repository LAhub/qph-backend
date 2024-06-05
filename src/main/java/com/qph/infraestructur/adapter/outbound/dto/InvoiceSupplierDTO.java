package com.qph.infraestructur.adapter.outbound.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceSupplierDTO {

    @NotNull
    private InvoiceDTO invoice;

    @NotNull
    private List<SupplierDTO> lstSupplier;
}
