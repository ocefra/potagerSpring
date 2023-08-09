package fr.eni.potager.dal;

import fr.eni.potager.bo.Potager;
import org.springframework.data.repository.CrudRepository;

public interface PotagerDAO extends CrudRepository<Potager, Integer> {

}
