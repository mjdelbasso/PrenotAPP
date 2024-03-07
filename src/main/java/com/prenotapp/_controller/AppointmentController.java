package com.prenotapp._controller;

import java.util.List;

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
import com.prenotapp._service.IAppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

  @Autowired
  private IAppointmentService service;

  @GetMapping
  public ResponseEntity<List<AppointmentDetailsDTO>> listAppointments()
    throws Exception {
    return new ResponseEntity<>(service.list(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AppointmentDetailsDTO> findAppointmentById(
    @PathVariable("id") Long id
  ) throws Exception {
    return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<AppointmentDetailsDTO> register(
    @Valid @RequestBody AppointmentDTO appointmentDTO
  ) throws Exception {
    if (service.appointmentAlreadyExist(appointmentDTO)) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    return new ResponseEntity<>(
      service.register(appointmentDTO),
      HttpStatus.CREATED
    );
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<AppointmentDetailsDTO> update(
    @Valid @RequestBody AppointmentDTO appointmentDTO,
    @PathVariable("id") Long id
  ) throws Exception {
    if (service.appointmentAlreadyExist(appointmentDTO)) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    service.update(appointmentDTO);

    return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    throws Exception {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
