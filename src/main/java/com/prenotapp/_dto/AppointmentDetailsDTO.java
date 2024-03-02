package com.prenotapp._dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AppointmentDetailsDTO {

  private Integer id;
  private String date;
  private ShopDetailsDTO shopDetails;
  private PersonaDetailsDTO personaDetails;

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class ShopDetailsDTO {

    private Integer shopId;
    private String shopName;
    private String address;
    private String city;
    private String phone;
  }

  @Data
  @AllArgsConstructor
  public static class PersonaDetailsDTO {

    private Integer personaId;
    private String personaFirstName;
    private String personaLastName;
    private String personaPhone;
  }
}
