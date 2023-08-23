package fr.eni.potager.ws.wrapper;


import fr.eni.potager.bll.JardinageManager;
import fr.eni.potager.bll.JardinageManagerImpl;
import fr.eni.potager.bo.Carre;
import fr.eni.potager.ws.dto.CarreDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CarreWrapper {
    @Autowired
    JardinageManager manager;

    public CarreDTO wrapCarre(Carre carre) {
        CarreDTO carreDTO = new CarreDTO();
        carreDTO.setSol(carre.getSol());
        carreDTO.setExposition(carre.getExposition());
        carreDTO.setPlantationList(manager.getAllPlantationOfCarre(carre));
        return carreDTO;
    }

}
