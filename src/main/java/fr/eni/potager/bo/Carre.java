package fr.eni.potager.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Carre implements Jardinable {
  @Id
  @GeneratedValue
  private Integer id;

  private Double surface;
  private Sol sol;
  private Exposition exposition;

  @ManyToOne
  private Potager potager;

  public Carre(Double surface, Sol sol, Exposition exposition, Potager potager) {
    this.surface = surface;
    this.sol = sol;
    this.exposition = exposition;
    this.potager = potager;
  }

  @Override
  public Double calculateSurface() {
    return this.getSurface();
  }


}
