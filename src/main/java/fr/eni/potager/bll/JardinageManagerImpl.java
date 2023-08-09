package fr.eni.potager.bll;

import fr.eni.potager.bo.Carre;
import fr.eni.potager.bo.Plantation;
import fr.eni.potager.bo.Plante;
import fr.eni.potager.bo.Potager;
import fr.eni.potager.dal.CarreDAO;
import fr.eni.potager.dal.PlantationDAO;
import fr.eni.potager.dal.PlanteDAO;
import fr.eni.potager.dal.PotagerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JardinageManagerImpl implements JardinageManager {
  @Autowired
  PlanteDAO planteDAO;
  @Autowired
  PotagerDAO potagerDAO;
  @Autowired
  CarreDAO carreDAO;
  @Autowired
  PlantationDAO plantationDAO;

  @Override
  public void addPlante(Plante plante) {
    planteDAO.save(plante);
  }

  @Override
  public List<Plante> getAllPlante() {
    return (List<Plante>) planteDAO.findAll();
  }

  @Override
  public void addPotager(Potager potager) {
    potagerDAO.save(potager);
  }

  @Override
  public List<Potager> getAllPotager() {
    return (List<Potager>) potagerDAO.findAll();
  }

  @Override
  public void addCarre(Carre carre) throws JardinageException {
    Double surfaceCarre = carre.getSurface();
    Double surfacePotager = carre.getPotager().getSurface();

    List<Carre> carresDuPotager = getAllCarreOfPotager(carre.getPotager());

    Double surfaceCarresExistants = 0.0;
    for (Carre c : carresDuPotager) {
      surfaceCarresExistants += c.getSurface();
    }
    Double surfaceRestante = surfacePotager - surfaceCarresExistants;

    if (surfaceRestante >= surfaceCarre) {
      carreDAO.save(carre);
    } else {
      throw new JardinageException(String.format("Le carré (%.2f) est trop grand : il ne reste plus que %.2f cm2.", surfaceCarre, surfaceRestante));
    }
  }

  @Override
  public List<Carre> getAllCarre() {
    return (List<Carre>) carreDAO.findAll();
  }

  @Override
  public List<Carre> getAllCarreOfPotager(Potager potager) {
    return carreDAO.findAllCarreOfPotager(potager);
  }


  @Override
  public void addPlantation(Plantation plantation) throws JardinageException {
    Double surfaceCarre = plantation.getCarre().getSurface();
    Double surfacePlantation = plantation.calculateSurface();

    List<Plantation> plantationsDuCarre = getAllPlantationOfCarre(plantation.getCarre());
    Double surfacePlantsExistants = 0.0;
    for (Plantation p:plantationsDuCarre) {
      surfacePlantsExistants += p.calculateSurface();
    }
    Double surfaceRestante = surfaceCarre - surfacePlantsExistants;
    if (surfaceRestante >= surfacePlantation) {
      plantationDAO.save(plantation);
    } else {
      throw new JardinageException(String.format("La plantation (%.2f) est trop grande : il ne reste plus que %.2f cm2.", surfacePlantation, surfaceRestante));
    }
  }

  @Override
  public List<Plantation> getAllPlantationOfCarre(Carre carre) {
    return plantationDAO.findAllPlantationOfCarre(carre);
  }
}
