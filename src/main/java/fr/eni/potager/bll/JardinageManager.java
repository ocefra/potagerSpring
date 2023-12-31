package fr.eni.potager.bll;

import fr.eni.potager.bo.*;

import java.util.List;

public interface JardinageManager <T extends Jardinable>{
  void addPlante(Plante... lst) throws JardinageException;
  List<Plante> getAllPlante();
  void locatePlante(Plante plante);

  void addPotager(Potager potager);
  List<Potager> getAllPotager();
  void visualizePotager(Potager chezOctavia);

//  void addCarre(Carre carre) throws JardinageException;
  List<Carre> getAllCarre();
  List<Carre> getAllCarreOfPotager(Potager potager);

//  void addPlantation(Plantation plantation) throws JardinageException;
  List<Plantation> getAllPlantation();
  List<Plantation> getAllPlantationOfCarre(Carre carre);
  void removePlantationFromCarre(Plante plante, Carre carre);

  void addAction(Action action) throws JardinageException;
  List<Action> getActionNextWeeks(Integer nWeeks);

  void addPlantationToCarre(Plantation plantation, Carre carre) throws JardinageException;

  void addCarreToPotager(Carre carre, Potager potager) throws JardinageException;
}
