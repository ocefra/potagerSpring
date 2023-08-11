package fr.eni.potager.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Action {
  @Id
  @GeneratedValue
  private Integer id;

  private String evenement;
  private LocalDate date;

  @ManyToOne
  private Actionable lieu;

  public Action(String evenement, Actionable lieu, LocalDate date) {
    this.evenement = evenement;
    this.lieu = lieu;
    this.date = date;
  }
}
