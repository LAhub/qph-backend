package com.qph.application.service;

import com.qph.application.port.ISupplierService;
import com.qph.domain.model.Supplier;
import com.qph.infraestructur.adapter.outbound.repo.IGenericRepo;
import com.qph.infraestructur.adapter.outbound.repo.ISupplierRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl extends CRUDImpl<Supplier, Integer> implements ISupplierService {

    private final ISupplierRepo repo;

    @Override
    protected IGenericRepo<Supplier, Integer> getRepo() {
        return repo;
    }
}
