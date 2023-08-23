package fr.eni.potager.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Potager extends Actionable implements Jardinable {
    private String ville;
    private String adresse;

    @OneToMany(mappedBy = "potager")
    @Delegate
    @ToString.Exclude
    private List<Carre> carreList = new ArrayList<>();

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
