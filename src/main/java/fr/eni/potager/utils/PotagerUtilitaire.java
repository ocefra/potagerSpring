package fr.eni.potager.utils;

import fr.eni.potager.bo.Jardin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotagerUtilitaire<T extends Jardin> {
    public Double squareCmToMeter(Double squareCm) {
        return squareCm/10000;
    }

    public Double calculateSurface(List<T> list) {
        return list.stream().mapToDouble(Jardin::calculateSurface).sum();
    }
}
