package com.prenotapp._dto;

import java.time.LocalDateTime;

import com.prenotapp._model.Persona;
import com.prenotapp._model.Shop;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDTO {

  private Integer id;

  @NotNull
  private LocalDateTime date;

  @NotNull
  private Persona persona;

  @NotNull
  private Shop shop;
}
