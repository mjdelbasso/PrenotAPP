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
public class ShopCategoryDTO {

  private Integer id;

  @NotNull
  private CategoryDTO category;

  @NotNull
  private ShopDTO shop;
}
