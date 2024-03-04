package com.prenotapp._controller;

import com.prenotapp._dto.AppointmentDTO;
import com.prenotapp._dto.AppointmentDetailsDTO;
import com.prenotapp._model.Appointment;
import com.prenotapp._service.IAppointmentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private IAppointmentService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<AppointmentDetailsDTO>> listAppointments() throws Exception {
        return ResponseEntity.ok(service.listAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDetailsDTO> findAppointmentById(@PathVariable("id") Integer id) throws Exception {
        return ResponseEntity.ok(service.findAppointmentById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<AppointmentDetailsDTO> register(@Valid @RequestBody AppointmentDTO appointmentDTO) throws Exception {
        Appointment appointment = mapper.map(appointmentDTO, Appointment.class);
        if (service.appointmentAlreadyExist(appointment)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Appointment registeredAppointment = service.register(appointment);
        URI location = new URI("/appointments/" + registeredAppointment.getId());
        return ResponseEntity.created(location).body(service.findAppointmentById(registeredAppointment.getId()));
    }

    @PutMapping("/update")
    public ResponseEntity<AppointmentDetailsDTO> update(@Valid @RequestBody AppointmentDTO appointmentDTO) throws Exception {
        Appointment appointment = mapper.map(appointmentDTO, Appointment.class);
        if (service.appointmentAlreadyExist(appointment)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        appointment = service.update(appointment);
        AppointmentDetailsDTO appointmentDetailsDTO = service.findAppointmentById(appointmentDTO.getId());
        return ResponseEntity.ok(appointmentDetailsDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
