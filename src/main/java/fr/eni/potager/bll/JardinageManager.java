package fr.eni.potager.bll;

import fr.eni.potager.bo.*;

import java.util.List;

public interface JardinageManager <T extends Jardinable>{
  void addPlante(Plante... lst) throws JardinageException;
  List<Plante> getAllPlante();

  void addPotager(Potager potager);
  List<Potager> getAllPotager();

  void addCarre(Carre carre) throws JardinageException;
  List<Carre> getAllCarre();
  List<Plantation> getAllPlantation();
  List<Carre> getAllCarreOfPotager(Potager potager);

  void addPlantation(Plantation plantation) throws JardinageException;

  List<Plantation> getAllPlantationOfCarre(Carre carre);

  void removePlantationFromCarre(Plante plante, Carre carre);

  void visualizePotager(Potager chezOctavia);

  void locatePlante(Plante plante);
}
