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
public class SocialDTO {

  private Integer id;

  private String facebook;

  private String instagram;

  private String twitter;

  private String pinterest;

  private String linkedin;

  private String youtube;

  private String tiktok;

  private String whatsapp;

  private String telegram;

  private List<ShopDTO> shops;
}
