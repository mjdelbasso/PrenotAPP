package com.prenotapp._dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategoryDTO {

  private Integer id;
  private String name;
  private String description;
}
