package fr.eni.potager.ws;

import fr.eni.potager.bll.JardinageManager;
import fr.eni.potager.bo.Carre;
import fr.eni.potager.bo.Jardinable;
import fr.eni.potager.bo.Potager;
import fr.eni.potager.ws.dto.CarreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jardinage")
public class PotagerWS {
    @Autowired
    JardinageManager manager;


   @GetMapping()
    public List<Jardinable> getAllPotager() {
       List<Jardinable> lst = new ArrayList<>();
       lst.addAll(manager.getAllPotager());
       lst.addAll(manager.getAllPlantation());
        return lst;
   }

    @GetMapping("/carre")
    public List<Carre> getAllCarre() {
       return manager.getAllCarre();
    }


}
