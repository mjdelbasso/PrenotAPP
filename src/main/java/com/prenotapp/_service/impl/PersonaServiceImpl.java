package com.prenotapp._service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prenotapp._dto.PersonaDTO;
import com.prenotapp._model.Persona;
import com.prenotapp._repo.IPersonaRepo;
import com.prenotapp._service.IPersonaService;

import lombok.NonNull;

@Service
public class PersonaServiceImpl implements IPersonaService {

  @Autowired
  private IPersonaRepo repo;

  @Autowired
  private ModelMapper mapper;

  @Override
  @Transactional(readOnly = true)
  public List<PersonaDTO> list() throws Exception {
    return repo
      .findAll()
      .stream()
      .map(this::toDTO)
      .sorted(Comparator.comparing(PersonaDTO::getId))
      .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public PersonaDTO register(@NonNull PersonaDTO personaDTO) throws Exception {
    return toDTO(repo.save(toEntity(personaDTO)));
  }

  @Override
  @Transactional
  public PersonaDTO update(@NonNull PersonaDTO personaDTO) throws Exception{
    return toDTO(repo.save(toEntity(personaDTO)));
  }

  @Override
  @Transactional(readOnly = true)
  public PersonaDTO findById(@NonNull Long id) throws Exception{
    return toDTO(repo.findById(id).get());
  }

  @Override
  @Transactional
  public void delete(@NonNull Long id) throws Exception{
    repo.deleteById(id);
  }

  private PersonaDTO toDTO(@NonNull Persona persona) {
    return mapper.map(persona, PersonaDTO.class);
  }

  private Persona toEntity(@NonNull PersonaDTO personaDTO) {
    return mapper.map(personaDTO, Persona.class);
  }
}
