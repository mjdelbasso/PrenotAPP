package com.prenotapp._service;

import com.prenotapp._dto.PersonaDTO;
import java.util.List;

public interface IPersonaService {
  public List<PersonaDTO> list() throws Exception;

  public PersonaDTO register(PersonaDTO personaDTO) throws Exception;

  public PersonaDTO update(PersonaDTO personaDTO) throws Exception;

  public PersonaDTO findById(Long id) throws Exception;

  public void delete(Long id) throws Exception;
}
