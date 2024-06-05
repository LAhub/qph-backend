package com.qph.application.service;

import com.qph.application.port.IClientService;
import com.qph.domain.model.Client;
import com.qph.infraestructur.adapter.outbound.repo.IGenericRepo;
import com.qph.infraestructur.adapter.outbound.repo.IClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends CRUDImpl<Client, Integer> implements IClientService {

    //@Autowired
    private final IClientRepo repo;// = new PatientRepoImpl();

    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return repo;
    }


}
