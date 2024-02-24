package com.prenotapp._controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.prenotapp._dto.PersonaDTO;
import com.prenotapp._model.Persona;
import com.prenotapp._service.IPersonaService;
import com.prenotapp.exception.ModelNotFoundException;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.Comparator;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/personas")
public class PersonaController {

  @Autowired
  private IPersonaService service;

  @Autowired
  private ModelMapper mapper;

  @GetMapping
  public ResponseEntity<List<PersonaDTO>> list() throws Exception {
    List<PersonaDTO> list = service
      .list()
      .stream()
      .map(this::convertToDto)
      .sorted(Comparator.comparing(PersonaDTO::getId))
      .collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonaDTO> listById(@PathVariable("id") Integer id)
    throws Exception {
    PersonaDTO personaDTO;
    Persona persona = service.listById(id);
    if (persona == null) {
      throw new ModelNotFoundException("ID NOT FOUND " + id);
    } else {
      personaDTO = convertToDto(persona);
    }
    return new ResponseEntity<>(personaDTO, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Void> register(
    @Valid @RequestBody PersonaDTO personaDTO
  ) throws Exception {
    Persona persona = mapper.map(personaDTO, Persona.class);
    Persona registeredPersona = service.register(persona);
    PersonaDTO registeredDTO = convertToDto(registeredPersona);

    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(registeredDTO.getId())
      .toUri();

    return ResponseEntity.created(location).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<PersonaDTO> update(
    @PathVariable("id") Integer id,
    @Valid @RequestBody PersonaDTO personaDTO
  ) throws Exception {
    Persona existingPersona = service.listById(id);

    if (existingPersona == null) {
      throw new ModelNotFoundException("ID NOT FOUND " + id);
    }

    personaDTO.setId(id);
    Persona persona = mapper.map(personaDTO, Persona.class);
    Persona updatedPersona = service.update(persona);
    PersonaDTO updatedDTO = convertToDto(updatedPersona);

    return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
    throws Exception {
    Persona existingPersona = service.listById(id);

    if (existingPersona == null) {
      throw new ModelNotFoundException("ID NOT FOUND " + id);
    }

    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @SuppressWarnings("null")
  @GetMapping("/hateoas/{id}")
  public EntityModel<PersonaDTO> listHateoas(@PathVariable("id") Integer id)
    throws Exception {
    Persona persona = service.listById(id);

    if (persona == null) {
      throw new ModelNotFoundException("ID NOT FOUND " + id);
    }

    PersonaDTO personaDTO = convertToDto(persona);

    EntityModel<PersonaDTO> resource = EntityModel.of(personaDTO);

    WebMvcLinkBuilder linkToPersona = linkTo(
      methodOn(this.getClass()).listById(id)
    );

    resource.add(linkToPersona.withRel("persona-link"));
    return resource;
  }

  // Método para convertir un objeto Persona a PersonaDTO
  private PersonaDTO convertToDto(Persona persona) {
    return mapper.map(persona, PersonaDTO.class);
  }
}
