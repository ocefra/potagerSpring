package fr.eni.potager.bll;

import fr.eni.potager.bo.*;
import fr.eni.potager.dal.*;
import fr.eni.potager.utils.PotagerUtilitaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JardinageManagerImpl implements JardinageManager<Jardinable> {
  @Autowired
  JardinageDAO dao;
  @Autowired
  PotagerUtilitaire util;

  @Override
  public void addPlante(Plante... lst) throws JardinageException {
    List<String> listError = new ArrayList<>();
    for(Plante p:lst) {
      try {
        dao.plante.save(p);
        System.out.println("-------test : "+p.getNom());
      } catch (Exception e) {
        listError.add("-----------Impossible d'ajouter la plante : " + p.getNom());
        System.out.println("-----------Impossible d'ajouter la plante : "+p.getNom());
      }
    }
    if (!listError.isEmpty()) {
      throw new JardinageException(listError);
    }
  }

  @Override
  public List<Plante> getAllPlante() {
    return (List<Plante>) dao.plante.findAll();
  }

  @Override
  public void addPotager(Potager potager) {
    dao.potager.save(potager);
  }

  @Override
  public List<Potager> getAllPotager() {
    return (List<Potager>) dao.potager.findAll();
  }

  @Override
  public void addCarre(Carre carre) throws JardinageException {
    Double surfaceCarre = carre.getSurface();
    Double surfacePotager = carre.getPotager().getSurface();

    List<Carre> carresDuPotager = getAllCarreOfPotager(carre.getPotager());

    Double surfaceCarresExistants = 0.0;
    surfaceCarresExistants = util.calculateSurface(carresDuPotager);

    Double surfaceRestante = surfacePotager - surfaceCarresExistants;

    if (surfaceRestante >= surfaceCarre) {
      dao.carre.save(carre);
    } else {
      throw new JardinageException(String.format(
              "Le carré (%.2f m2) est trop grand : il ne reste plus que %.2f m2.",
              util.squareCmToMeter(surfaceCarre), util.squareCmToMeter(surfaceRestante)));
    }
  }

  @Override
  public List<Carre> getAllCarre() {
    return (List<Carre>) dao.carre.findAll();
  }

  @Override
  public List<Plantation> getAllPlantation() {
    return (List<Plantation>) dao.plantation.findAll();
  }

  @Override
  public List<Carre> getAllCarreOfPotager(Potager potager) {
    return dao.carre.findAllCarreOfPotager(potager);
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

    surfacePlantsExistants = util.calculateSurface(plantationsDuCarre);

    Double surfaceRestante = surfaceCarre - surfacePlantsExistants;
    if (surfaceRestante >= surfacePlantation) {
      dao.plantation.save(plantation);
    } else {
      throw new JardinageException(String.format(
              "La plantation (%.2f m2) est trop grande : il ne reste plus que %.2f m2.",
              util.squareCmToMeter(surfacePlantation), util.squareCmToMeter(surfaceRestante)));
    }
  }

  @Override
  public List<Plantation> getAllPlantationOfCarre(Carre carre) {
    return dao.plantation.findAllPlantationOfCarre(carre);
  }

  @Transactional
  @Override
  public void removePlantationFromCarre(Plante plante, Carre carre) {
    dao.plantation.deleteByPlanteAndCarre(plante, carre);
  }

  public void visualizePotager(Potager potager) {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("\n====== Potager : %s =======", potager.getNom()));

    for(Carre c:dao.carre.findAllCarreOfPotager(potager)) {
      sb.append("\nCarré "+c.getNom() +" contenant :");
      for(Plantation p: dao.plantation.findAllPlantationOfCarre(c)){
        sb.append(p.synthesePlantation());
      }
    }
    System.out.println(sb);
  }

  public void locatePlante(Plante plante) {
    StringBuilder sb = new StringBuilder();
    sb.append(plante.getVariete() +" "+ plante.getNom());

        for (Plantation plantation: getAllPlantation()) {
          if (plante.equals(plantation.getPlante())) {
            sb.append(String.format("\n- Potager : %s | Carré : %s | Quantité : %d", plantation.getCarre().getPotager().getNom(), plantation.getCarre().getNom(), plantation.getQuantite()));
          }
        }

    System.out.println(sb);
  }

}
