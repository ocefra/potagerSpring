package fr.eni.potager.dal;

import fr.eni.potager.bo.Action;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActionDAO extends CrudRepository<Action, Integer> {
//  @Query("SELECT a FROM Action a WHERE a.date > CURRENT_DATE");
//  List<Action> findNextWeeks(Integer numWeeks);
}
