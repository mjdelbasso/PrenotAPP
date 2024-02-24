package com.prenotapp._dto;

import java.util.List;
import lombok.Data;

@Data
public class ShopCategoryDTO {

  private ShopDTO shop;
  private List<CategoryDTO> categories;
}
