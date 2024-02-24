package com.prenotapp._dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;


import lombok.Data;

@Data
public class ShopDTO {

  private Integer id;

  @NotNull
  private String shopName;

  @NotNull
  private String description;

  @NotNull
  private String phone;

  @NotNull
  private String website;

  @NotNull
  private String email;

  @NotNull
  private String address;

  @NotNull
  private String city;

  @NotNull
  private String zipCode;

  @NotNull
  private String country;

  private List<CategoryDTO> categories;
}
