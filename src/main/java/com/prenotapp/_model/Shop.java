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
@Table(
    name = "shops",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"shop_name"}, name = "uk_shop_name"),
        @UniqueConstraint(columnNames = {"email"}, name = "uk_shop_email")
    }
)
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

  @Column(name = "email", unique = true) // Añadimos unique = true aquí también
  private String email;

  @Column(name = "website")
  private String website;

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
