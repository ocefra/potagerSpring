package fr.eni.potager.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Carre extends Actionable implements Jardinable {
  private Sol sol;
  private Exposition exposition;

  @ManyToOne
  @JsonIgnore
  @ToString.Exclude
  private Potager potager;

  @OneToMany(mappedBy = "carre")
  @ToString.Exclude
  @Delegate
  private List<Plantation> plantationList = new ArrayList<>();

//  public Carre(String nom, Double surface, Sol sol, Exposition exposition, Potager potager) {
  public Carre(String nom, Double surface, Sol sol, Exposition exposition) {
    super(nom, surface);
    this.sol = sol;
    this.exposition = exposition;
//    this.potager = potager;
  }

  @Override
  public Double calculateSurface() {
    return this.getSurface();
  }

}
