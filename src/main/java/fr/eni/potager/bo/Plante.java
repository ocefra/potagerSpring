package fr.eni.potager.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "nom", "variete" }) })
public class Plante {
  @Id
  @GeneratedValue
  private Integer id;

  private String nom;
  private TypePlante type;
  private String variete;
  private Double surface;
  private Exposition exposition;

  public Plante(String nom, TypePlante type, String variete, Double surface, Exposition exposition) {
    this.nom = nom;
    this.type = type;
    this.variete = variete;
    this.surface = surface;
    this.exposition = exposition;
  }
}
