package com.prenotapp._service;

import com.prenotapp._dto.AppointmentDetailsDTO;
import com.prenotapp._model.Appointment;
import java.util.List;

public interface IAppointmentService extends ICRUD<Appointment, Long> {
  public List<AppointmentDetailsDTO> listAppointments() throws Exception;

  public AppointmentDetailsDTO findAppointmentById(Long idAppointment)
    throws Exception;

  public boolean appointmentAlreadyExist(Appointment appointment)
    throws Exception;
}
