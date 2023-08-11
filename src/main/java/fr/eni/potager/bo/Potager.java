package fr.eni.potager.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Potager {
    @Id
    @GeneratedValue
    private Integer id;

    private String nom;
    private String ville;
    private String adresse;
    private Double surface;

    public Potager(String nom, String ville, String adresse, Double surface) {
        this.nom = nom;
        this.ville = ville;
        this.adresse = adresse;
        this.surface = surface;
    }

}
