package fr.eni.potager.bll;

import fr.eni.potager.bo.Plante;

import java.util.List;

public interface PotagerManager {
  void addPlante(Plante plante);

  List<Plante> getAllPlante();
}
