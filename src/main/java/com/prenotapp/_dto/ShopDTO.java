package com.prenotapp._dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class ShopDTO {

  private Integer id;

  private String website;

  private String email;

  private String description;

  private List<CategoryDTO> categories;

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
}
