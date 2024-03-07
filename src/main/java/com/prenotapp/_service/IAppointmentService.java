package com.prenotapp._service;

import com.prenotapp._dto.AppointmentDTO;
import com.prenotapp._dto.AppointmentDetailsDTO;
import java.util.List;

public interface IAppointmentService {
  public boolean appointmentAlreadyExist(AppointmentDTO appointment)
    throws Exception;

  public List<AppointmentDetailsDTO> list() throws Exception;

  public AppointmentDetailsDTO findById(Long id) throws Exception;

  public AppointmentDetailsDTO register(AppointmentDTO appointment)
    throws Exception;

  public AppointmentDetailsDTO update(AppointmentDTO appointment)
    throws Exception;

  public void delete(Long id) throws Exception;
}
