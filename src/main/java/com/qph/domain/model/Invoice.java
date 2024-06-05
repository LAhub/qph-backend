package com.qph.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idInvoice;

    @ManyToOne //FK
    @JoinColumn(name = "id_client", nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_PATIENT"))
    private Client client;

    @ManyToOne //FK
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_USER"))
    private User user;

    //@DateTimeFormat(pattern = "")
    @Column(nullable = false) //ISODate yyyy-MM-DDTHH:mm:ss
    private LocalDateTime consultDate;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceDetail> details;

}
