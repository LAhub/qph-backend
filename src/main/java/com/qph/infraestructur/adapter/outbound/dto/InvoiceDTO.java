package com.qph.infraestructur.adapter.outbound.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InvoiceDTO {

    @EqualsAndHashCode.Include
    private Integer idInvoice;

    @NotNull
    private ClientDTO client;

    @NotNull
    private Integer idUser;

    @NotNull
    private String numConsult;

    @NotNull
    private LocalDateTime consultDate;

    @NotNull
    @JsonManagedReference
    private List<InvoiceDetailDTO> details;
}
