package fr.eni.potager;

import fr.eni.potager.bll.PotagerManager;
import fr.eni.potager.bo.Exposition;
import fr.eni.potager.bo.Plante;
import fr.eni.potager.bo.Potager;
import fr.eni.potager.bo.TypePlante;
import fr.eni.potager.dal.PlanteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PotagerApplication implements CommandLineRunner {
  @Autowired
  PotagerManager manager;

  public static void main(String[] args) {
    SpringApplication.run(PotagerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Plante tomateMarmande = new Plante("Marmande", TypePlante.FRUIT, "tomate", 2500., Exposition.SOLEIL);
    Plante carotteVosges = new Plante("Blanche des Vosges", TypePlante.RACINE, "carotte", 900., Exposition.MI_OMBRE);

    manager.addPlante(tomateMarmande);
    manager.addPlante(carotteVosges);

    manager.getAllPlante().forEach(System.out::println);

    Potager chezOctavia = new Potager("Chez Octavia", "Brécé", "123 rue de Rennes",1.0e6);
    Potager chezTeck = new Potager("Chez Teck", "Chantepie", "321 rue de St-Malo",9.0e5);

    manager.addPotager(chezOctavia);
    manager.addPotager(chezTeck);

    manager.getAllPotager().forEach(System.out::println);
  }
}
