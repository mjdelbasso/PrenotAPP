package com.prenotapp._controller;

import com.prenotapp._dto.PersonaDTO;
import com.prenotapp._service.IPersonaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

  @GetMapping
  public ResponseEntity<List<PersonaDTO>> list() throws Exception {
    return new ResponseEntity<>(service.list(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonaDTO> findById(@PathVariable("id") Long id)
    throws Exception {
    return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<PersonaDTO> register(
    @Valid @RequestBody PersonaDTO personaDTO
  ) throws Exception {
    return new ResponseEntity<>(
      service.register(personaDTO),
      HttpStatus.CREATED
    );
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<PersonaDTO> update(
    @PathVariable("id") Long id,
    @RequestBody PersonaDTO personaDTO
  ) throws Exception {
    personaDTO.setId(id);

    return new ResponseEntity<>(
      service.update(personaDTO),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    throws Exception {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
