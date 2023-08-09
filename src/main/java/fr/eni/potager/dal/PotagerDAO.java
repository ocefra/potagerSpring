package fr.eni.potager.dal;

import fr.eni.potager.bo.Potager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PotagerDAO extends CrudRepository<Potager, Integer> {
}


