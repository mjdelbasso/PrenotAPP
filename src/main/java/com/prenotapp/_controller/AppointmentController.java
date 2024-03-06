package com.prenotapp._controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

  @Autowired
  private IAppointmentService service;

  @Autowired
  private ModelMapper mapper;

  @GetMapping
  public ResponseEntity<List<AppointmentDetailsDTO>> listAppointments()
    throws Exception {
    return new ResponseEntity<>(service.listAppointments(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AppointmentDetailsDTO> findAppointmentById(
    @PathVariable("id") Integer id
  ) throws Exception {
    return new ResponseEntity<>(service.findAppointmentById(id), HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<AppointmentDetailsDTO> register(
    @Valid @RequestBody AppointmentDTO appointmentDTO
  ) throws Exception {
    Appointment appointment = toEntity(appointmentDTO);
    if (service.appointmentAlreadyExist(appointment)) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    Appointment registeredAppointment = service.register(appointment);

    return new ResponseEntity<>(
      service.findAppointmentById(registeredAppointment.getId()),
      HttpStatus.CREATED
    );
  }

  @PutMapping("/update")
  public ResponseEntity<AppointmentDetailsDTO> update(
    @Valid @RequestBody AppointmentDTO appointmentDTO
  ) throws Exception {
    Appointment appointment = toEntity(appointmentDTO);
    if (service.appointmentAlreadyExist(appointment)) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    appointment = service.update(appointment);
    AppointmentDetailsDTO appointmentDetailsDTO = service.findAppointmentById(
      appointmentDTO.getId()
    );
    return new ResponseEntity<>(appointmentDetailsDTO, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
    throws Exception {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  public AppointmentDTO toDTO(Appointment appointment) {
    return mapper.map(appointment, AppointmentDTO.class);
  }

  public Appointment toEntity(AppointmentDTO appointmentDTO) {
    return mapper.map(appointmentDTO, Appointment.class);
  }
}
