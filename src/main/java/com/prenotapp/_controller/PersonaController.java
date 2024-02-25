package com.prenotapp._controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.prenotapp._dto.PersonaDTO;
import com.prenotapp._model.Persona;
import com.prenotapp._service.IPersonaService;
import com.prenotapp.exception.ModelNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    List<Persona> lstPersona = service.list();
    List<PersonaDTO> lstPersonaDTO = lstPersona
      .stream()
      .map(p -> mapper.map(p, PersonaDTO.class))
      .collect(Collectors.toList());
    return new ResponseEntity<>(lstPersonaDTO, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonaDTO> listById(@PathVariable("id") Integer id)
    throws Exception {
    Persona persona = service.listById(id);
    PersonaDTO personaDTO = mapper.map(persona, PersonaDTO.class);
    return new ResponseEntity<>(personaDTO, HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<PersonaDTO> register(
    @Valid @RequestBody PersonaDTO personaDTO
  ) throws Exception {
    Persona persona = mapper.map(personaDTO, Persona.class);
    persona = service.register(persona);
    personaDTO = mapper.map(persona, PersonaDTO.class);
    return new ResponseEntity<>(personaDTO, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PersonaDTO> update(
    @PathVariable("id") Integer id,
    @RequestBody PersonaDTO personaDTO
  ) throws Exception {
    Persona persona = mapper.map(personaDTO, Persona.class);
    persona.setId(id);
    persona = service.update(persona);
    personaDTO = mapper.map(persona, PersonaDTO.class);
    return new ResponseEntity<>(personaDTO, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
    throws Exception {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @SuppressWarnings("null")
  @GetMapping("/hateoas/{id}")
  public EntityModel<PersonaDTO> listByIdHateoas(
    @PathVariable("id") Integer id
  ) throws Exception {
    Persona persona = service.listById(id);
    if (persona == null) {
      throw new ModelNotFoundException("Persona not found with ID: " + id);
    }
    PersonaDTO personaDTO = mapper.map(persona, PersonaDTO.class);
    EntityModel<PersonaDTO> model = EntityModel.of(personaDTO);
    WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).list());
    model.add(linkTo.withRel("all-personas"));
    return model;
  }
}
