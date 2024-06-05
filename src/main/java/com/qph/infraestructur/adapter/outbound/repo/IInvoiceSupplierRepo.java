package com.qph.infraestructur.adapter.outbound.repo;

import com.qph.domain.model.InvoiceSupplier;
import com.qph.domain.model.InvoiceSupplierPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IInvoiceSupplierRepo extends IGenericRepo<InvoiceSupplier, InvoiceSupplierPK> {


    @Modifying
    @Query(value = "INSERT INTO invoice_supplier(id_invoice, id_supplier) VALUES (:idConsult, :idExam)", nativeQuery = true)
    Integer saveExam(@Param("idConsult") Integer idConsult, @Param("idExam") Integer id);
}
