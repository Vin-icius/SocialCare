package br.unoeste.fipp.socialcare.Services;


import br.unoeste.fipp.socialcare.DataBase.entities.Actions;
import br.unoeste.fipp.socialcare.DataBase.repositories.actionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class actionsService {

    @Autowired
    private actionsRepository acRepo;

    public Actions addAction (Actions action) {
        return acRepo.save(action);
    }

    public boolean deleteById (Long id) {
        try{
            acRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public Actions getById (Long id) {
        Actions action = acRepo.findById(id).get();
        return action;
    }
    public List<Actions> getAll() {
        return acRepo.findAll();
    }
}
