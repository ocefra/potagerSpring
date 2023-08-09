package fr.eni.potager.dal;

import fr.eni.potager.bo.Carre;
import fr.eni.potager.bo.Potager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarreDAO extends CrudRepository<Carre, Integer> {
  @Query("SELECT c FROM Carre c WHERE c.potager=:pot")
  List<Carre> findAllCarreOfPotager(@Param("pot") Potager potager);
}
