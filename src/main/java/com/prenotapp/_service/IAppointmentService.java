package com.prenotapp._service;

import java.util.List;

import com.prenotapp._dto.AppointmentDetailsDTO;
import com.prenotapp._model.Appointment;

public interface IAppointmentService extends ICRUD<Appointment, Integer> {
  public List<AppointmentDetailsDTO> listAppointments() throws Exception;

  public AppointmentDetailsDTO findAppointmentById(Integer idAppointment)
    throws Exception;

  public boolean AppointmentAlredyExist(Appointment appointment)
    throws Exception;
}
