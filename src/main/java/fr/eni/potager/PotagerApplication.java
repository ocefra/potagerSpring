package fr.eni.potager;

import fr.eni.potager.bll.PotagerException;
import fr.eni.potager.bll.PotagerManager;
import fr.eni.potager.bo.*;
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

    // ========== plantes ============= //
    Plante tomateMarmande = new Plante("Marmande", TypePlante.FRUIT, "tomate", 2500., Exposition.SOLEIL);
    Plante carotteVosges = new Plante("Blanche des Vosges", TypePlante.RACINE, "carotte", 900., Exposition.MI_OMBRE);

    manager.addPlante(tomateMarmande);
    manager.addPlante(carotteVosges);

    printSeparatorLine("Test liste de toutes les plantes disponibles");
    manager.getAllPlante().forEach(System.out::println);

    // ========== potager ============= //
    Potager chezOctavia = new Potager("Chez Octavia", "Brécé", "123 rue de Rennes",1.0e6);
    Potager chezTeck = new Potager("Chez Teck", "Chantepie", "321 rue de St-Malo",9.0e5);

    manager.addPotager(chezOctavia);
    manager.addPotager(chezTeck);

    printSeparatorLine("Test liste de tous les potagers");
    manager.getAllPotager().forEach(System.out::println);

    // ========== carrés ============= //
    Carre carre1 = new Carre();
    carre1.setPotager(chezTeck);
    carre1.setSol(Sol.SABLEUX);
    carre1.setExposition(Exposition.MI_OMBRE);
    carre1.setSurface(3.0e5);

    Carre carre2 = new Carre(3.0e5, Sol.HUMIFERE, Exposition.SOLEIL, chezOctavia);
    Carre carre3 = new Carre(2.0e5, Sol.LIMONEUX, Exposition.OMBRE, chezOctavia);
    Carre carre4 = new Carre(10.5e5, Sol.ARGILEUX, Exposition.MI_OMBRE, chezOctavia);

    manager.addCarre(carre1);
    manager.addCarre(carre2);
    manager.addCarre(carre3);

    printSeparatorLine("Test liste de tous les carrés");
    manager.getAllCarre().forEach(System.out::println);

    printSeparatorLine("Test liste carrés par potager");
    manager.getAllCarreOfPotager(chezOctavia).forEach(System.out::println);

    printSeparatorLine("Test ajout carré trop grand");
    try {
      manager.addCarre(carre4);
    } catch (PotagerException e) {
      System.out.println("ERREUR : " + e.getMessage());
    }
  }

  private void printSeparatorLine(String message) {
    String separatorSequence = "===========";
    System.out.printf("%n%s %s %s", separatorSequence, message, separatorSequence);
  }
}
