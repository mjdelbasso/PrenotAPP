package com.prenotapp._dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShopSocialDTO {

  private Integer id;

  private SocialDTO socials;

  private ShopDTO shop;
}
