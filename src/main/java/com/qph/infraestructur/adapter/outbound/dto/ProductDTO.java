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
public class ProductDTO {
    @EqualsAndHashCode.Include
    private Integer idProduct;
    @NotNull
    private String nameProduct;
    @NotNull
    private String description;
    @NotNull
    private Double price;
    @NotNull
    private Integer stock;

}
