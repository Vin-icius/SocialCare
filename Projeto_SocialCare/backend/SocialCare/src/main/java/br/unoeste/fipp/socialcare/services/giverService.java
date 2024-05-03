package br.unoeste.fipp.socialcare.services;

import br.unoeste.fipp.socialcare.db.entities.Giver;
import br.unoeste.fipp.socialcare.db.entities.Person;
import br.unoeste.fipp.socialcare.db.repositories.giverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class giverService {
    @Autowired
    private giverRepository giverRepo;
    public Giver getById(Long id){
        Giver giver = giverRepo.findById(id).get();
        return giver;
    }

    public List<Giver> getAll(){
        return giverRepo.findAll();
    }

    public Giver addGiver(Giver giver){
        return giverRepo.save(giver);
    }

    public boolean deleteById(Long id){
        try{
            giverRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
