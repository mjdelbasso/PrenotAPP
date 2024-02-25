package com.prenotapp._service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenotapp._model.Appointment;
import com.prenotapp._repo.IAppointmentRepo;
import com.prenotapp._service.IAppointmentService;

@Service
public class AppointmentServiceImpl
  extends CRUDImpl<Appointment, Integer>
  implements IAppointmentService {

  @Autowired
  private IAppointmentRepo repo;

  @Override
  protected IAppointmentRepo getRepo() {
    return repo;
  }

    

}
