package com.prenotapp._dto;

import com.prenotapp._model.Persona;
import com.prenotapp._model.Shop;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AppointmentDTO {

  private Long id;

  @NotNull
  private LocalDateTime date;

  @NotNull
  private Persona persona;

  @NotNull
  private Shop shop;
}
