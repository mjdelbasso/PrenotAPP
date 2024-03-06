package com.prenotapp._dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonaDTO {

  private Long id;

  private String email;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  private String phone;
}
