package fr.eni.potager.bll;

import fr.eni.potager.bo.Carre;
import fr.eni.potager.bo.Plante;
import fr.eni.potager.bo.Potager;

import java.util.List;

public interface PotagerManager {
  void addPlante(Plante plante);
  List<Plante> getAllPlante();

  void addPotager(Potager potager);
  List<Potager> getAllPotager();

  void addCarre(Carre carre) throws PotagerException;
  List<Carre> getAllCarre();
  List<Carre> getAllCarreOfPotager(Potager potager);
}
