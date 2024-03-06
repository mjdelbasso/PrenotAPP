package com.prenotapp._model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
  name = "shop_category",
  uniqueConstraints = {
    @UniqueConstraint(
      columnNames = { "category_id", "shop_id" },
      name = "uk_category_id_shop_id"
    ),
  }
)
public class ShopCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(
    name = "category_id",
    referencedColumnName = "id",
    nullable = false,
    foreignKey = @ForeignKey(name = "fk_shop_category_category_id")
  )
  private Category category;

  @ManyToOne
  @JoinColumn(
    name = "shop_id",
    referencedColumnName = "id",
    nullable = false,
    foreignKey = @ForeignKey(name = "fk_shop_category_shop_id")
  )
  private Shop shop;
}
