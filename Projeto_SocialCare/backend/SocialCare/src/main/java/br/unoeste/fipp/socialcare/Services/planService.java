package br.unoeste.fipp.socialcare.Services;


import br.unoeste.fipp.socialcare.DataBase.entities.Plan;
import br.unoeste.fipp.socialcare.DataBase.repositories.planRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class planService {

    @Autowired
    private planRepository planRepo;

    public Plan addPlan (Plan plan) {
        return planRepo.save(plan);
    }

    public boolean deleteById (Long id) {
        try{
            planRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public Plan getById (Long id) {
        Plan plan = planRepo.findById(id).get();
        return plan;
    }

    public List<Plan> getAll() {
        return planRepo.findAll();
    }
}
