package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.FisicalPerson;
import br.unoeste.fipp.socialcare.DataBase.repositories.fisicalPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class fisicalPersonService {
    @Autowired
    private fisicalPersonRepository giverRepo;

    public FisicalPerson addFisicalPerson (FisicalPerson fisicalPerson) {
        return giverRepo.save(fisicalPerson);
    }

    public boolean deleteById (Long id) {
        try{
            giverRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public FisicalPerson getById (Long id) {
        FisicalPerson fisicalPerson = giverRepo.findById(id).get();
        return fisicalPerson;
    }

    public List<FisicalPerson> getAll (){
        return giverRepo.findAll();
    }
}