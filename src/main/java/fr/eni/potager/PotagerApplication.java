package fr.eni.potager;

import fr.eni.potager.bll.JardinageException;
import fr.eni.potager.bll.JardinageManager;
import fr.eni.potager.bo.*;
import fr.eni.potager.utils.PotagerUtilitaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class PotagerApplication implements CommandLineRunner {
  @Autowired
  JardinageManager manager;


  public static void main(String[] args) {
    SpringApplication.run(PotagerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    // ========== plantes ============= //
    Plante tomateMarmande = new Plante("Marmande", TypePlante.FRUIT, "tomate", 2500., Exposition.SOLEIL);
    Plante carotteVosges = new Plante("Blanche des Vosges", TypePlante.RACINE, "carotte", 900., Exposition.MI_OMBRE);
    Plante pdtNouvelle = new Plante("Nouvelle", TypePlante.RACINE, "pomme de terre", 1500., Exposition.OMBRE);
    Plante pdtNouvelle2 = new Plante("Ancienne", TypePlante.RACINE, "pomme de terre", 1500., Exposition.SOLEIL);
    Plante pdtNouvelle3 = new Plante("Nouvelle", TypePlante.RACINE, "pomme de terre", 1500., Exposition.SOLEIL);
    Plante tomateCerise = new Plante("Cerise", TypePlante.FRUIT, "tomate", 500., Exposition.MI_OMBRE);


    printSeparatorLine("Test ajout doublon plante");
    try {
      manager.addPlante(tomateMarmande, carotteVosges, tomateCerise, tomateMarmande, pdtNouvelle, pdtNouvelle3, pdtNouvelle2, tomateMarmande);
    } catch (JardinageException e) {
      System.out.println(e.getListError());
    }

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
    carre1.setNom("A");
    carre1.setPotager(chezTeck);
    carre1.setSol(Sol.SABLEUX);
    carre1.setExposition(Exposition.MI_OMBRE);
    carre1.setSurface(3.0e5);

    Carre carre2 = new Carre("A", 8.0e5, Sol.HUMIFERE, Exposition.SOLEIL, chezOctavia);
    Carre carre3 = new Carre("B",2.0e5, Sol.LIMONEUX, Exposition.OMBRE, chezOctavia);
    Carre carre4 = new Carre("D", 10.5e5, Sol.ARGILEUX, Exposition.MI_OMBRE, chezOctavia);

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
    } catch (JardinageException e) {
      System.out.println("ERREUR : " + e.getMessage());
    }

    printSeparatorLine("Test ajout plantation");
    Plantation plantation1 = new Plantation(tomateMarmande,carre3,3, LocalDate.now(),LocalDate.now().plusDays(30));
    Plantation plantation2 = new Plantation(carotteVosges,carre3,2, LocalDate.now(),LocalDate.now().plusDays(50));
    Plantation plantation3 = new Plantation(carotteVosges,carre3,1, LocalDate.now(),LocalDate.now().plusDays(50));
    Plantation plantation4 = new Plantation(pdtNouvelle,carre3,2, LocalDate.now(),LocalDate.now().plusDays(50));
    Plantation plantation6 = new Plantation(pdtNouvelle2,carre3,2, LocalDate.now(),LocalDate.now().plusDays(50));
    Plantation plantation7 = new Plantation(tomateCerise,carre2,1, LocalDate.now(),LocalDate.now().plusDays(50));
    Plantation carottePotagerTeck = new Plantation(carotteVosges,carre1,2, LocalDate.now(),LocalDate.now().plusDays(50));
    Plantation pdtPotagerTeck = new Plantation(pdtNouvelle,carre1,5, LocalDate.now(),LocalDate.now().plusDays(50));


    manager.addPlantation(plantation1);
    manager.addPlantation(plantation2);
    manager.addPlantation(plantation4);
    manager.addPlantation(plantation3);
    manager.addPlantation(plantation7);
    manager.addPlantation(carottePotagerTeck);
    manager.addPlantation(pdtPotagerTeck);


    manager.getAllPlantationOfCarre(carre3).forEach(System.out::println);

    printSeparatorLine("Test ajout plantation trop grande");
    Plantation plantation5 = new Plantation(pdtNouvelle, carre3,300, LocalDate.now(),LocalDate.now().plusDays(30));
    try {
      manager.addPlantation(plantation5);
    } catch (JardinageException e) {
      System.out.println("ERREUR : " + e.getMessage());
    }


    printSeparatorLine("Test visualisation potager");
    manager.visualizePotager(chezOctavia);
    manager.visualizePotager(chezTeck);

//    printSeparatorLine("Test suppression de toutes les plantations d'une même plante dans un carré");
//    manager.removePlantationFromCarre(carotteVosges, carre3);
//    System.out.println("Plantations restantes après la suppression :");
//    manager.getAllPlantationOfCarre(carre3).forEach(System.out::println);

    printSeparatorLine("Test localisation plante");
    manager.locatePlante(carotteVosges);

    // ============= ACTION ================ //
    Action a1 = new Action("récolter tomates", carre1, LocalDate.now().plusDays(10));
    Action a2 = new Action("désherber", chezTeck, LocalDate.now().plusDays(20));
    Action a3 = new Action("élaguer", chezOctavia, LocalDate.now().plusDays(-20));

    printSeparatorLine("Test ajout actions");
    manager.addAction(a1);
    manager.addAction(a2);
    try {
      manager.addAction(a3);
    } catch (JardinageException e) {
      System.out.println("ERREUR : " + e.getMessage());
    }

  }



  private void printSeparatorLine(String message) {
    String separatorSequence = "===========";
    System.out.printf("%n%s %s %s%n", separatorSequence, message, separatorSequence);
  }
}
