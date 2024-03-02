package com.prenotapp._model;

import jakarta.persistence.*;
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
@Table(name = "shops")
public class Shop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "shop_name", nullable = false, unique = true)
  private String shopName;

  @Column(name = "address")
  private String address;

  @Column(name = "city")
  private String city;

  @Column(name = "country")
  private String country;

  @Column(name = "phone")
  private String phone;

  @Column(name = "email")
  private String email;

  @ManyToMany
  @JoinTable(
    name = "shop_socials",
    joinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(
      name = "social_id",
      referencedColumnName = "id"
    ),
    foreignKey = @ForeignKey(name = "fk_shop_socials_shop_id"),
    inverseForeignKey = @ForeignKey(name = "fk_shop_socials_social_id")
  )
  private List<Social> socials;

  @ManyToMany
  @JoinTable(
    name = "shop_category",
    joinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(
      name = "category_id",
      referencedColumnName = "id"
    ),
    foreignKey = @ForeignKey(name = "fk_shop_category_shop_id"),
    inverseForeignKey = @ForeignKey(name = "fk_shop_category_category_id")
  )
  private List<Category> categories;
}
