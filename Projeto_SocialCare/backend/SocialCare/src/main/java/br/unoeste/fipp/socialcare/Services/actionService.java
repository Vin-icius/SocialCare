package br.unoeste.fipp.socialcare.Services;


import br.unoeste.fipp.socialcare.DataBase.entities.Action;
import br.unoeste.fipp.socialcare.DataBase.repositories.actionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class actionService {

    @Autowired
    private actionRepository acRepo;

    public Action addAction (Action action) {
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

    public Action getById (Long id) {
        Action action = acRepo.findById(id).get();
        return action;
    }
    public List<Action> getAll() {
        return acRepo.findAll();
    }
}
