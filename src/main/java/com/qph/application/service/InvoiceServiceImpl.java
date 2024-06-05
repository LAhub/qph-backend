package com.qph.application.service;

import com.qph.domain.model.Invoice;
import com.qph.domain.model.Supplier;
import com.qph.infraestructur.adapter.outbound.repo.IInvoiceSupplierRepo;
import com.qph.infraestructur.adapter.outbound.repo.IGenericRepo;
import com.qph.infraestructur.adapter.outbound.repo.IInvoiceRepo;
import com.qph.application.port.IInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl extends CRUDImpl<Invoice, Integer> implements IInvoiceService {

    private final IInvoiceRepo invoiceRepo;
    private final IInvoiceSupplierRepo ceRepo;

    @Override
    protected IGenericRepo<Invoice, Integer> getRepo() {
        return invoiceRepo;
    }

    @Transactional
    @Override
    public Invoice saveTransactional(Invoice invoice, List<Supplier> suppliers) {
        invoiceRepo.save(invoice); //GUARDAR MAESTRO DETALLE
        suppliers.forEach(ex -> ceRepo.saveExam(invoice.getIdInvoice(), ex.getIdSupplier()));

        return invoice;
    }
}
