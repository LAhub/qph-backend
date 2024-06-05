package com.qph.infraestructur.adapter.inbound.rest;

import com.qph.infraestructur.adapter.outbound.dto.SupplierDTO;
import com.qph.domain.model.Supplier;
import com.qph.application.port.ISupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final ISupplierService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> findAll() {
        List<SupplierDTO> list = service.findAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> findById(@PathVariable("id") Integer id) {
        Supplier obj = service.findById(id);

        return ResponseEntity.ok(convertToDto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SupplierDTO dto) {
        Supplier obj = service.save(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSupplier()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody SupplierDTO dto) {
        dto.setIdSupplier(id);
        Supplier obj = service.update(id, convertToEntity(dto));

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<SupplierDTO> findByIdHateoas(@PathVariable("id") Integer id) {
        EntityModel<SupplierDTO> resource = EntityModel.of(convertToDto(service.findById(id)));

        //generar link informativo
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("exam-self-info"));
        resource.add(link2.withRel("exam-all-info"));

        return resource;
    }

    private SupplierDTO convertToDto(Supplier obj) {
        return mapper.map(obj, SupplierDTO.class);
    }

    private Supplier convertToEntity(SupplierDTO dto) {
        return mapper.map(dto, Supplier.class);
    }

    /*public ExamController(IExamService service) {
        this.service = service;
    }*/

    /*@GetMapping
    public Exam findById() {
        return service.findByIdAndValidate(5);
    }*/
}
