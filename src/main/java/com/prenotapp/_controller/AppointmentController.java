package com.prenotapp._controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prenotapp._dto.AppointmentDTO;
import com.prenotapp._dto.AppointmentDetailsDTO;
import com.prenotapp._model.Appointment;
import com.prenotapp._service.IAppointmentService;
import com.prenotapp.exception.ModelNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

  @Autowired
  private IAppointmentService appointmentService;

  @Autowired
  private ModelMapper mapper;

  @GetMapping
  public ResponseEntity<List<AppointmentDetailsDTO>> list() throws Exception {
    List<Appointment> appointments = appointmentService.list();
    List<AppointmentDetailsDTO> appointmentDTOs = appointments
      .stream()
      .map(this::convertToDTO)
      .collect(Collectors.toList());
    return new ResponseEntity<>(appointmentDTOs, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AppointmentDetailsDTO> listById(
    @PathVariable("id") Integer id
  ) throws Exception {
    Appointment appointment = appointmentService.listById(id);
    if (appointment == null) {
      throw new ModelNotFoundException("Appointment not found");
    }
    AppointmentDetailsDTO dto = convertToDTO(appointment);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<Void> register(
    @Valid @RequestBody AppointmentDTO appointmentDTO
  ) throws Exception {
    Appointment appointment = mapper.map(appointmentDTO, Appointment.class);
    Appointment registeredAppointment = appointmentService.register(
      appointment
    );
    URI location = new URI("/appointments/" + registeredAppointment.getId());
    return ResponseEntity.created(location).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<AppointmentDetailsDTO> update(
    @PathVariable("id") Integer id,
    @Valid @RequestBody AppointmentDTO appointmentDTO
  ) throws Exception {
    Appointment appointment = mapper.map(appointmentDTO, Appointment.class);
    appointment.setId(id);
    Appointment updatedAppointment = appointmentService.update(appointment);
    AppointmentDetailsDTO dto = convertToDTO(updatedAppointment);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  private AppointmentDetailsDTO convertToDTO(Appointment appointment) {
    AppointmentDetailsDTO dto = new AppointmentDetailsDTO();
    dto.setIdAppointment(appointment.getId());
    dto.setDate(appointment.getDate());
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
