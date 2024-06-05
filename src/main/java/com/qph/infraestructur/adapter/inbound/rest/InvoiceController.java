package com.qph.infraestructur.adapter.inbound.rest;

import com.qph.infraestructur.adapter.outbound.dto.InvoiceDTO;
import com.qph.infraestructur.adapter.outbound.dto.InvoiceSupplierDTO;
import com.qph.domain.model.Invoice;
import com.qph.domain.model.Supplier;
import com.qph.application.port.IInvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final IInvoiceService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> findAll() {
        List<InvoiceDTO> list = service.findAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> findById(@PathVariable("id") Integer id) {
        Invoice obj = service.findById(id);

        return ResponseEntity.ok(convertToDto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody InvoiceSupplierDTO dto) {
        List<Supplier> suppliers = mapper.map(dto.getLstSupplier(), new TypeToken<List<Supplier>>(){}.getType());
        Invoice obj = service.saveTransactional(convertToEntity(dto.getInvoice()), suppliers);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdInvoice()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody InvoiceDTO dto) {
        Invoice obj = service.update(id, convertToEntity(dto));

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<InvoiceDTO> findByIdHateoas(@PathVariable("id") Integer id) {
        EntityModel<InvoiceDTO> resource = EntityModel.of(convertToDto(service.findById(id)));

        //generar link informativo
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("consult-self-info"));
        resource.add(link2.withRel("consult-all-info"));

        return resource;
    }

    private InvoiceDTO convertToDto(Invoice obj) {
        return mapper.map(obj, InvoiceDTO.class);
    }

    private Invoice convertToEntity(InvoiceDTO dto) {
        return mapper.map(dto, Invoice.class);
    }

    /*public ConsultController(IConsultService service) {
        this.service = service;
    }*/

    /*@GetMapping
    public Consult findById() {
        return service.findByIdAndValidate(5);
    }*/
}
