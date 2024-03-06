package com.prenotapp._dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AppointmentDetailsDTO {

  private Long id;
  private String date;
  private ShopDetailsDTO shopDetails;
  private PersonaDetailsDTO personaDetails;

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class ShopDetailsDTO {

    private Long shopId;
    private String shopName;
    private String address;
    private String city;
    private String phone;
  }

  @Data
  @AllArgsConstructor
  public static class PersonaDetailsDTO {

    private Long personaId;
    private String personaFirstName;
    private String personaLastName;
    private String personaPhone;
  }
}
