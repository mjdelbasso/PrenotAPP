package com.prenotapp._service.impl;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenotapp._dto.AppointmentDetailsDTO;
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

  @Override
  public List<AppointmentDetailsDTO> listAppointments() {
    List<Appointment> appointments = repo.findAll();
    return appointments
      .stream()
      .map(this::convertToDTO)
      .collect(Collectors.toList());
  }

  @Override
  public AppointmentDetailsDTO findAppointmentById(Integer idAppointment) {
    if (idAppointment == null || idAppointment <= 0) {
      return null;
    }

    Appointment appointment = repo.findById(idAppointment).orElse(null);
    return convertToDTO(appointment);
  }

  @Override
  public boolean AppointmentAlredyExist(Appointment appointment) {
    return repo.existsByPersonaIdAndShopIdAndDate(
      appointment.getPersona().getId(),
      appointment.getShop().getId(),
      appointment.getDate()
    );
  }

  private AppointmentDetailsDTO convertToDTO(Appointment appointment) {
    AppointmentDetailsDTO dto = new AppointmentDetailsDTO();
    dto.setId(appointment.getId());
    dto.setDate(
      appointment.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    );
    dto.setShopName(appointment.getShop().getShopName());
    dto.setShopAddress(appointment.getShop().getAddress());
    dto.setShopCity(appointment.getShop().getCity());
    dto.setShopPhone(appointment.getShop().getPhone());
    dto.setPersonaFirstName(appointment.getPersona().getFirstName());
    dto.setPersonaLastName(appointment.getPersona().getLastName());
    dto.setPersonaPhone(appointment.getPersona().getPhone());
    return dto;
  }
}
