package fr.eni.potager.utils;

import fr.eni.potager.bo.Jardinable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotagerUtilitaire<T extends Jardinable> {
    public Double squareCmToMeter(Double squareCm) {
        return squareCm/10000;
    }

    public Double calculateSurface(List<T> list) {
        return list.stream().mapToDouble(Jardinable::calculateSurface).sum();
    }
}
