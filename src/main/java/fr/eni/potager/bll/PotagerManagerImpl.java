package fr.eni.potager.bll;

import fr.eni.potager.bo.Carre;
import fr.eni.potager.bo.Plante;
import fr.eni.potager.bo.Potager;
import fr.eni.potager.dal.CarreDAO;
import fr.eni.potager.dal.PlanteDAO;
import fr.eni.potager.dal.PotagerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotagerManagerImpl implements PotagerManager {
  @Autowired
  PlanteDAO planteDAO;
  @Autowired
  PotagerDAO potagerDAO;
  @Autowired
  CarreDAO carreDAO;

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
  public void addCarre(Carre carre) throws PotagerException {
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
      throw new PotagerException(String.format("Le carré (%.2f) est trop grand : il ne reste plus que %.2f cm2.", surfaceCarre, surfaceRestante));
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
}
