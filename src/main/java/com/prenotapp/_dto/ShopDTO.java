package com.prenotapp._dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShopDTO {

  private Integer id;

  @NotNull
  private String shopName;

  @NotNull
  private String phone;

  @NotNull
  private String address;

  @NotNull
  private String city;

  @NotNull
  private String zipCode;

  @NotNull
  private String country;

  private String website;

  private String email;

  private String description;

  private List<CategoryDTO> categories;
}
