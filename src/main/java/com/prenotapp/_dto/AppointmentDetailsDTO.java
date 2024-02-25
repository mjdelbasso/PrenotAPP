package com.prenotapp._dto;

import lombok.Data;

@Data
public class AppointmentDetailsDTO {

  private Integer id;
  private String date;
  private String shopName;
  private String shopAddress;
  private String shopCity;
  private String shopPhone;
  private String personaFirstName;
  private String personaLastName;
  private String personaPhone;
}
