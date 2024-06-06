package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.LegalPerson;
import br.unoeste.fipp.socialcare.DataBase.repositories.legalPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class legalPersonService {
    @Autowired
    private legalPersonRepository giverRepo;

    public LegalPerson addLegalPerson (LegalPerson legalPerson) {
        return giverRepo.save(legalPerson);
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

    public LegalPerson getById (Long id) {
        LegalPerson legalPerson = giverRepo.findById(id).get();
        return legalPerson;
    }

    public List<LegalPerson> getAll (){
        return giverRepo.findAll();
    }
}