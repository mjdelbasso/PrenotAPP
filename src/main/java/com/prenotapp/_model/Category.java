package com.prenotapp._model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    name = "categories",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"category_name"}, name = "uk_category_name")
    }
)
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "category_name", nullable = false, unique = true)
  private String categoryName;

  @Column(name = "description")
  private String description;

  @ManyToMany(mappedBy = "categories")
  private List<Shop> shops;

}
