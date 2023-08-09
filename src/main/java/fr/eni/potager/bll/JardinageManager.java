package fr.eni.potager.bll;

import fr.eni.potager.bo.Carre;
import fr.eni.potager.bo.Plantation;
import fr.eni.potager.bo.Plante;
import fr.eni.potager.bo.Potager;

import java.util.List;

public interface JardinageManager {
  void addPlante(Plante plante) throws JardinageException;
  List<Plante> getAllPlante();

  void addPotager(Potager potager);
  List<Potager> getAllPotager();

  void addCarre(Carre carre) throws JardinageException;
  List<Carre> getAllCarre();
  List<Carre> getAllCarreOfPotager(Potager potager);

  void addPlantation(Plantation plantation) throws JardinageException;

  List<Plantation> getAllPlantationOfCarre(Carre carre);

}
