package com.prenotapp._model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(
  name = "shops",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = { "shop_name" }, name = "uk_shop_name"),
    @UniqueConstraint(columnNames = { "email" }, name = "uk_shop_email"),
  }
)
public class Shop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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

  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "website")
  private String website;

  @ManyToMany(fetch = FetchType.EAGER)
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
