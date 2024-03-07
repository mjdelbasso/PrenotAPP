package com.prenotapp._service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenotapp._dto.PersonaDTO;
import com.prenotapp._model.Persona;
import com.prenotapp._repo.IPersonaRepo;
import com.prenotapp._service.IPersonaService;

import lombok.NonNull;

@Service
@SuppressWarnings("null")
public class PersonaServiceImpl implements IPersonaService {

  @Autowired
  private IPersonaRepo repo;

  @Autowired
  private ModelMapper mapper;

  @Override
  public List<PersonaDTO> list() {
    return repo
      .findAll()
      .stream()
      .map(this::toDTO)
      .sorted(Comparator.comparing(PersonaDTO::getId))
      .collect(Collectors.toList());
  }

  @Override
  public PersonaDTO register(@NonNull PersonaDTO personaDTO) {
    return toDTO(repo.save(toEntity(personaDTO)));
  }

  @Override
  public PersonaDTO update(@NonNull PersonaDTO personaDTO) {
    return toDTO(repo.save(toEntity(personaDTO)));
  }

  @Override
  public PersonaDTO findById(@NonNull Long id) {
    return toDTO(repo.findById(id).get());
  }

  @Override
  public void delete(@NonNull Long id) {
    repo.deleteById(id);
  }

  public PersonaDTO toDTO(@NonNull Persona persona) {
    return mapper.map(persona, PersonaDTO.class);
  }

  public Persona toEntity(@NonNull PersonaDTO personaDTO) {
    return mapper.map(personaDTO, Persona.class);
  }
}
