package com.prenotapp._dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShopDTO {

  private Integer id;

  private String shopName;

  private String address;

  private String city;

  private String country;

  private String phone;

  private String email;

  private List<String> socials;

  private List<CategoryDTO> categories;

}


