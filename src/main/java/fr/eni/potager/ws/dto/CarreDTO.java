package fr.eni.potager.ws.dto;

import fr.eni.potager.bll.JardinageManager;
import fr.eni.potager.bll.JardinageManagerImpl;
import fr.eni.potager.bo.Carre;
import fr.eni.potager.bo.Exposition;
import fr.eni.potager.bo.Plantation;
import fr.eni.potager.bo.Sol;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CarreDTO {

    private Sol sol;
    private Exposition exposition;
    private List<Plantation> plantationList = new ArrayList<>();

//    public CarreDTO(Carre carre) {
//        JardinageManager manager = new JardinageManagerImpl();
//        this.sol = carre.getSol();
//        this.exposition = carre.getExposition();
//        this.plantationList = manager.getAllPlantationOfCarre(carre);
//    }


}
