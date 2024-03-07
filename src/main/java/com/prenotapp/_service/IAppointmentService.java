package com.prenotapp._service;

import com.prenotapp._dto.AppointmentDTO;

public interface IAppointmentService {
  public boolean appointmentAlreadyExist(AppointmentDTO appointment)
    throws Exception;
}
