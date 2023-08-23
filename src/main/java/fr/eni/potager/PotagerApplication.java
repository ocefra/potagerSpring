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
    Plantation plantation1 = new Plantation(tomateMarmande,3, LocalDate.now(),LocalDate.now().plusDays(30));
    manager.addPlantationToCarre(plantation1,carre3);
    Plantation plantation2 = new Plantation(carotteVosges,2, LocalDate.now(),LocalDate.now().plusDays(50));
    manager.addPlantationToCarre(plantation2,carre3);
    Plantation plantation3 = new Plantation(carotteVosges,1, LocalDate.now(),LocalDate.now().plusDays(50));
    manager.addPlantationToCarre(plantation3,carre3);
    Plantation plantation4 = new Plantation(pdtNouvelle,2, LocalDate.now(),LocalDate.now().plusDays(50));
    manager.addPlantationToCarre(plantation4,carre3);
//    Plantation plantation6 = new Plantation(pdtNouvelle2,carre3,2, LocalDate.now(),LocalDate.now().plusDays(50));
//    manager.addPlantationToCarre(plantation6,carre3);
    Plantation plantation7 = new Plantation(tomateCerise,1, LocalDate.now(),LocalDate.now().plusDays(50));
    manager.addPlantationToCarre(plantation7,carre2);
    Plantation carottePotagerTeck = new Plantation(carotteVosges,2, LocalDate.now(),LocalDate.now().plusDays(50));
    manager.addPlantationToCarre(carottePotagerTeck,carre1);
    Plantation pdtPotagerTeck = new Plantation(pdtNouvelle,5, LocalDate.now(),LocalDate.now().plusDays(50));
    manager.addPlantationToCarre(pdtPotagerTeck,carre1);



    manager.getAllPlantationOfCarre(carre3).forEach(System.out::println);
//
    printSeparatorLine("Test ajout plantation trop grande");
    Plantation plantation6 = new Plantation(pdtNouvelle2,2, LocalDate.now(),LocalDate.now().plusDays(50));
    try {
    manager.addPlantationToCarre(plantation6,carre3);
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

//    printSeparatorLine("Test liste de tous les carrés");
//    manager.getAllCarre().forEach(System.out::println);

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

    printSeparatorLine("Test test");
    System.out.println(carre3.getPlantationList().size());

    printSeparatorLine("Test récupération actions futures");
    manager.getActionNextWeeks(3).forEach(System.out::println);
  }



  private void printSeparatorLine(String message) {
    String separatorSequence = "===========";
    System.out.printf("%n%s %s %s%n", separatorSequence, message, separatorSequence);
  }
}
