package com.qph.infraestructur.adapter.inbound.rest;

import com.qph.infraestructur.adapter.outbound.dto.ClientDTO;
import com.qph.domain.model.Client;
import com.qph.application.port.IClientService;
import com.qph.infraestructur.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
//@RequestMapping("${client.controller.path}")
@RequestMapping("/clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClientController {

    //@Autowired
    private final IClientService service;// = new PatientServiceImpl();
    /*@Qualifier("defaultMapper")
    private final ModelMapper mapper;/
     */

    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        //List<PatientDTO> list = service.findAll().stream().map(p -> new PatientDTO(p.getIdPatient(), p.getFirstName(), p.getLastName(), p.getDni(), p.getAddress(), p.getPhone(), p.getEmail())).toList();
        //List<PatientDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        List<ClientDTO> list = mapperUtil.mapList(service.findAll(), ClientDTO.class);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable("id") Integer id) {
        Client obj =  service.findById(id);

        //return ResponseEntity.ok(convertToDto(obj));
        return ResponseEntity.ok(mapperUtil.map(obj, ClientDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ClientDTO dto) {
        //Patient obj = service.save(convertToEntity(dto));
        Client obj = service.save(mapperUtil.map(dto, Client.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdClient()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ClientDTO dto) {
        dto.setIdClient(id);
        //Patient obj = service.update(id, convertToEntity(dto));
        Client obj = service.update(id, mapperUtil.map(dto, Client.class));

        //return ResponseEntity.ok(convertToDto(obj));
        return ResponseEntity.ok(mapperUtil.map(obj, ClientDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<ClientDTO> findByIdHateoas(@PathVariable("id") Integer id) {
        //EntityModel<PatientDTO> resource = EntityModel.of(convertToDto(service.findById(id)));
        EntityModel<ClientDTO> resource = EntityModel.of(mapperUtil.map(service.findById(id), ClientDTO.class));

        //generar link informativo
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("patient-self-info"));
        resource.add(link2.withRel("patient-all-info"));

        return resource;
    }

    /*private PatientDTO convertToDto(Patient obj) {
        return mapper.map(obj, PatientDTO.class);
    }

    private Patient convertToEntity(PatientDTO dto) {
        return mapper.map(dto, Patient.class);
    }*/

    /*public PatientController(IPatientService service) {
        this.service = service;
    }*/

    /*@GetMapping
    public Patient findById() {
        return service.findByIdAndValidate(5);
    }*/
}
