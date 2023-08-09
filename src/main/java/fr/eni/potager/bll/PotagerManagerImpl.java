package fr.eni.potager.bll;

import fr.eni.potager.bo.Plante;
import fr.eni.potager.bo.Potager;
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
}
