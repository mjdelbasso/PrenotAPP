package com.prenotapp._controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.prenotapp._dto.PersonaDTO;
import com.prenotapp._model.Persona;
import com.prenotapp._service.IPersonaService;
import com.prenotapp.exception.ModelNotFoundException;
import jakarta.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
public class PersonaController {

  @Autowired
  private IPersonaService service;

  @Autowired
  private ModelMapper mapper;

  @GetMapping
  public ResponseEntity<List<PersonaDTO>> list() throws Exception {
    return new ResponseEntity<>(
      service
        .list()
        .stream()
        .map(persona -> toDTO(persona))
        .sorted(Comparator.comparing(PersonaDTO::getId))
        .collect(Collectors.toList()),
      HttpStatus.OK
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonaDTO> findById(@PathVariable("id") Integer id)
    throws Exception {
    return new ResponseEntity<>(toDTO(service.findById(id)), HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<PersonaDTO> register(
    @Valid @RequestBody PersonaDTO personaDTO
  ) throws Exception {
    Persona persona = toEntity(personaDTO);
    persona = service.register(persona);
    return new ResponseEntity<>(toDTO(persona), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PersonaDTO> update(
    @PathVariable("id") Integer id,
    @RequestBody PersonaDTO personaDTO
  ) throws Exception {
    Persona persona = toEntity(personaDTO);
    persona.setId(id);
    persona = service.update(persona);
    return new ResponseEntity<>(toDTO(persona), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
    throws Exception {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @SuppressWarnings("null")
  @GetMapping("/hateoas/{id}")
  public EntityModel<PersonaDTO> findByIdHateoas(
    @PathVariable("id") @NonNull Integer id
  ) throws Exception {
    Persona persona = service.findById(id);
    if (persona == null) {
      throw new ModelNotFoundException("Persona not found with ID: " + id);
    }
    PersonaDTO personaDTO = toDTO(persona);
    EntityModel<PersonaDTO> model = EntityModel.of(personaDTO);
    model.add(linkTo(methodOn(this.getClass()).list()).withRel("all-personas"));
    return model;
  }

  public PersonaDTO toDTO(Persona persona) {
    return mapper.map(persona, PersonaDTO.class);
  }

  public Persona toEntity(PersonaDTO personaDTO) {
    return mapper.map(personaDTO, Persona.class);
  }
}
