package com.prenotapp._dto;

import java.util.ArrayList;
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

  private Long id;

  private String shopName;

  private String address;

  private String city;

  private String country;

  private String phone;

  private String email;

  private String website;

  @Builder.Default
  private List<CategoryDTO> categories = new ArrayList<>();
}
