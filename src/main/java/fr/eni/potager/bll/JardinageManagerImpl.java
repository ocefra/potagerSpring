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

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
  public void addPlante(Plante plante) throws JardinageException {
    try {
      planteDAO.save(plante);
    } catch (Exception e) {
      throw new JardinageException("Impossible d'ajouter la plante : " + e.getMessage());
    }
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
      throw new JardinageException(String.format("Le carré (%.2f cm2) est trop grand : il ne reste plus que %.2f cm2.", surfaceCarre, surfaceRestante));
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
    List<Plantation> plantationsDuCarre = getAllPlantationOfCarre(plantation.getCarre());

    // === Contrainte max 3 plantes ===
    boolean max3ok = false;
    Set<Plante> plantesDuCarre = new HashSet<>();
    for (Plantation p : plantationsDuCarre) {
      plantesDuCarre.add(p.getPlante());
    }
    plantesDuCarre.add(plantation.getPlante());
    max3ok = plantesDuCarre.size() <= 3;

    if (!max3ok) {
      throw new JardinageException("Le carré contient déjà 3 espèces différentes");
    }

    // Si la première contrainte passe
    // === Contrainte surface ===
    Double surfaceCarre = plantation.getCarre().getSurface();
    Double surfacePlantation = plantation.calculateSurface();

    Double surfacePlantsExistants = 0.0;
    for (Plantation p : plantationsDuCarre) {
      surfacePlantsExistants += p.calculateSurface();
    }
    Double surfaceRestante = surfaceCarre - surfacePlantsExistants;
    if (surfaceRestante >= surfacePlantation) {
      plantationDAO.save(plantation);
    } else {
      throw new JardinageException(String.format("La plantation (%.2f cm2) est trop grande : il ne reste plus que %.2f cm2.", surfacePlantation, surfaceRestante));
    }
  }

  @Override
  public List<Plantation> getAllPlantationOfCarre(Carre carre) {
    return plantationDAO.findAllPlantationOfCarre(carre);
  }
}
