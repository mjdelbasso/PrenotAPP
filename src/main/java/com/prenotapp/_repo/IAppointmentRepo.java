package com.prenotapp._repo;

import java.time.LocalDateTime;

import com.prenotapp._model.Appointment;

public interface IAppointmentRepo extends IGenericRepo<Appointment, Long> {
  boolean existsByPersonaIdAndShopIdAndDate(
    Long personaId,
    Long shopId,
    LocalDateTime date
  );
}
