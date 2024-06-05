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

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idClient;

    @Column(nullable = false, length = 70)//, name = "nombre")
    private String firstName; //camelCase lowerCamelCase UpperCamelCase | snake first_name

    @Column(nullable = false, length = 70)
    private String lastName;

    @Column(nullable = false, length = 10)
    private String idCard;

    @Column(length = 150)
    private String address;

    @Column(nullable = false, length = 9)
    private String phone;

    @Column(nullable = false, length = 55)
    private String email;

}
