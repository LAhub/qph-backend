package com.qph.infraestructur.adapter.outbound.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Integer idClient;

    @NotNull
    //@NotEmpty
    //@NotBlank
    @Size(min = 3, max = 70, message = "{firstname.size}")
    private String firstName;

    @NotNull
    @Size(min = 3, max = 70, message = "{lastname.size}")
    private String lastName;

    @NotNull
    private String idCard;

    @NotNull
    private String address;

    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String phone;

    @Email
    private String email;
}
