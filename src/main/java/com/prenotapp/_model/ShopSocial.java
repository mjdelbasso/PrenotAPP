  package com.prenotapp._model;

  import jakarta.persistence.Entity;
  import jakarta.persistence.GeneratedValue;
  import jakarta.persistence.GenerationType;
  import jakarta.persistence.Id;
  import jakarta.persistence.JoinColumn;
  import jakarta.persistence.ManyToOne;
  import jakarta.persistence.Table;
  import lombok.AllArgsConstructor;
  import lombok.Builder;
  import lombok.Data;
  import lombok.NoArgsConstructor;

  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @Data
  @Entity
  @Table(name = "shop_socials")
  public class ShopSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id", nullable = false)
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "social_id", referencedColumnName = "id", nullable = false)
    private Social social;
    
  }
