package com.qph.application.port;

import com.qph.domain.model.Invoice;
import com.qph.domain.model.Supplier;

import java.util.List;

public interface IInvoiceService extends ICRUD<Invoice, Integer> {

    Invoice saveTransactional(Invoice invoice, List<Supplier> suppliers);
}
