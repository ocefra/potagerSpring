package fr.eni.potager.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Plantation implements Jardinable {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Plante plante;

    @ManyToOne
    private Carre carre;

    private Integer quantite;
    private LocalDate datePlantation;
    private LocalDate dateRecolte;

    public Plantation(Plante plante, Carre carre, Integer quantite, LocalDate datePlantation, LocalDate dateRecolte) {
        this.plante = plante;
        this.carre = carre;
        this.quantite = quantite;
        this.datePlantation = datePlantation;
        this.dateRecolte = dateRecolte;
    }

    public Double calculateSurface() {
        return this.quantite * this.plante.calculateSurface();
    }

    public String synthesePlantation() {
        return String.format("%n - %d plant(s) de %s",this.quantite, this.plante.getNom());
    }
}
