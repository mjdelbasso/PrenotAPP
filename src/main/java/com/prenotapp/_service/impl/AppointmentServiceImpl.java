package com.prenotapp._service.impl;

import com.prenotapp._dto.AppointmentDetailsDTO;
import com.prenotapp._model.Appointment;
import com.prenotapp._model.Persona;
import com.prenotapp._model.Shop;
import com.prenotapp._repo.IAppointmentRepo;
import com.prenotapp._service.IAppointmentService;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
      appointment
        .getDate()
        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm"))
    );

    Shop shop = appointment.getShop();
    Persona persona = appointment.getPersona();

    AppointmentDetailsDTO.ShopDetailsDTO shopDetailsDTO = new AppointmentDetailsDTO.ShopDetailsDTO(
      shop.getId(),
      shop.getShopName(),
      shop.getAddress(),
      shop.getCity(),
      shop.getPhone()
    );
    dto.setShopDetails(shopDetailsDTO);

    AppointmentDetailsDTO.PersonaDetailsDTO personaDetailsDTO = new AppointmentDetailsDTO.PersonaDetailsDTO(
      persona.getId(),
      persona.getFirstName(),
      persona.getLastName(),
      persona.getPhone()
    );
    dto.setPersonaDetails(personaDetailsDTO);

    return dto;
  }
}
