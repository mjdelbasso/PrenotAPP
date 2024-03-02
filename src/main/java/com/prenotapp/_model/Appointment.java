package com.prenotapp._model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
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
@Table(name = "appointments")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "persona_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_appointments_persona_id"))
  private Persona persona;

  @ManyToOne
  @JoinColumn(name = "shop_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_appointments_shop_id"))
  private Shop shop;

  @Column(name = "date", nullable = false)
  private LocalDateTime date;
}
