package com.prenotapp._model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "socials")
public class Social {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "facebook")
  private String facebook;

  @Column(name = "instagram")
  private String instagram;

  @Column(name = "twitter")
  private String twitter;

  @Column(name = "pinterest")
  private String pinterest;

  @Column(name = "linkedin")
  private String linkedin;

  @Column(name = "youtube")
  private String youtube;

  @Column(name = "tiktok")
  private String tiktok;

  @Column(name = "whatsapp")
  private String whatsapp;

  @Column(name = "telegram")
  private String telegram;

  @ManyToMany
  @JoinTable(
    name = "shop_socials",
    joinColumns = @JoinColumn(name = "shop_id"),
    inverseJoinColumns = @JoinColumn(name = "social_id")
  )
  private List<Shop> shops;
}
