package fr.eni.potager.dal;

import fr.eni.potager.bo.Carre;
import fr.eni.potager.bo.Plantation;
import fr.eni.potager.bo.Plante;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlantationDAO extends CrudRepository<Plantation, Integer> {
  @Query("SELECT p FROM Plantation p WHERE p.carre=:carre")
  List<Plantation> findAllPlantationOfCarre(@Param("carre") Carre carre);

  @Modifying
  @Query("DELETE FROM Plantation p WHERE p.carre = :carre AND p.plante = :plante")
  void deleteByPlanteAndCarre(@Param("plante") Plante plante, @Param("carre") Carre carre);
}
