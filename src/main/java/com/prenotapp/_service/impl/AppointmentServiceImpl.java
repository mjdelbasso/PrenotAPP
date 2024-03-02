package com.prenotapp._service.impl;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenotapp._dto.AppointmentDetailsDTO;
import com.prenotapp._model.Appointment;
import com.prenotapp._model.Persona;
import com.prenotapp._model.Shop;
import com.prenotapp._repo.IAppointmentRepo;
import com.prenotapp._service.IAppointmentService;

@Service
public class AppointmentServiceImpl extends CRUDImpl<Appointment, Integer> implements IAppointmentService {

    @Autowired
    private IAppointmentRepo repo;

    @Override
    protected IAppointmentRepo getRepo() {
        return repo;
    }

    @Override
    public List<AppointmentDetailsDTO> listAppointments() {
        return repo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public AppointmentDetailsDTO findAppointmentById(Integer idAppointment) {
        if (idAppointment == null || idAppointment <= 0) {
            return null;
        }
        return convertToDTO(repo.findById(idAppointment).orElse(null));
    }

    @Override
    public boolean appointmentAlreadyExist(Appointment appointment) {
        return repo.existsByPersonaIdAndShopIdAndDate(appointment.getPersona().getId(), appointment.getShop().getId(), appointment.getDate());
    }

    private AppointmentDetailsDTO convertToDTO(Appointment appointment) {
        if (appointment == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm");
        Shop shop = appointment.getShop();
        Persona persona = appointment.getPersona();

        return new AppointmentDetailsDTO(
                appointment.getId(),
                appointment.getDate().format(formatter),
                new AppointmentDetailsDTO.ShopDetailsDTO(shop.getId(), shop.getShopName(), shop.getAddress(), shop.getCity(), shop.getPhone()),
                new AppointmentDetailsDTO.PersonaDetailsDTO(persona.getId(), persona.getFirstName(), persona.getLastName(), persona.getPhone())
        );
    }
}
