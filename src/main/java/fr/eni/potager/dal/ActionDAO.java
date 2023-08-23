package fr.eni.potager.dal;

import fr.eni.potager.bo.Action;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface ActionDAO extends CrudRepository<Action, Integer> {
//  @Query("SELECT a FROM Action a WHERE a.date > CURRENT_DATE")
//  @Query("SELECT a FROM Action a WHERE a.date > DATE_ADD(CURRENT_DATE, INTERVAL 2 WEEK)") // doesn't work
//  @Query("SELECT a FROM Action a WHERE a.date > ?#{@actionDAO.next2weeks()}")
//  List<Action> findNextWeeks(Integer nWeeks);
//  List<Action> findNext2Weeks();

  // https://stackoverflow.com/questions/50821879/how-to-call-sql-dateadd-method-using-jpa-query
//  default Instant next2weeks() {
//    return Instant.now().plus(2 * 7, ChronoUnit.DAYS);
//  }
}
