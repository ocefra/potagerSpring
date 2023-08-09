package fr.eni.potager.dal;

import fr.eni.potager.bo.Plante;
import org.springframework.data.repository.CrudRepository;

public interface PlanteDAO extends CrudRepository<Plante, Integer> {
}
