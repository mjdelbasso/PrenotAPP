package com.prenotapp._repo;

import java.time.LocalDateTime;

import com.prenotapp._model.Appointment;

public interface IAppointmentRepo extends IGenericRepo<Appointment, Integer> {

    boolean existsByPersonaIdAndShopIdAndDate(Integer personaId, Integer shopId, LocalDateTime date);
}
