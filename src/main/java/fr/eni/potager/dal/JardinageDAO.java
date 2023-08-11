package fr.eni.potager.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JardinageDAO {
    @Autowired
    public PlanteDAO plante;
    @Autowired
    public PotagerDAO potager;
    @Autowired
    public CarreDAO carre;
    @Autowired
    public PlantationDAO plantation;
    @Autowired
    public ActionDAO action;

}
