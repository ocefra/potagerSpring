package fr.eni.potager.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Potager extends Actionable implements Jardinable {
    private String ville;
    private String adresse;

    public Potager(String nom, String ville, String adresse, Double surface) {
        super(nom, surface);
        this.ville = ville;
        this.adresse = adresse;
    }

    @Override
    public Double calculateSurface() {
        return getSurface();
    }
}
