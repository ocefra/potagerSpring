package fr.eni.potager.bo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
//@DiscriminatorValue()
public abstract class Actionable {
  @Id
  @GeneratedValue
  private Integer id;

  private String nom;
  private Double surface;

  public Actionable(String nom, Double surface) {
    this.nom = nom;
    this.surface = surface;
  }
}
