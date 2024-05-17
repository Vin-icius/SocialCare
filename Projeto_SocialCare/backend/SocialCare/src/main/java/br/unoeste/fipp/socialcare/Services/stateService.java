package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.State;
import br.unoeste.fipp.socialcare.DataBase.repositories.stateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class stateService {

    @Autowired
    private stateRepository stateRepo;

    public State getById(Long id){
        State state = stateRepo.findById(id).get();
        return state;
    }

    public List<State> getAll(){
        return stateRepo.findAll();
    }

    public State addState(State state){
        return stateRepo.save(state);
    }

    public boolean deleteById(Long id){
        try{
            stateRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
