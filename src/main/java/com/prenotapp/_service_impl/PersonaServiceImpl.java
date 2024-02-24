package com.prenotapp._service_impl;

import com.prenotapp._model.Persona;
import com.prenotapp._repo.IPersonaRepo;
import com.prenotapp._service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl
  extends CRUDImpl<Persona, Integer>
  implements IPersonaService {

  @Autowired
  private IPersonaRepo repo;

  @Override
  protected IPersonaRepo getRepo() {
    return repo;
  }
}
