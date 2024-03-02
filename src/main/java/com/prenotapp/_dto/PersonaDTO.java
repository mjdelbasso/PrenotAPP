package com.prenotapp._dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonaDTO {

  private Integer id;

  private String email;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  private String phone;
}
