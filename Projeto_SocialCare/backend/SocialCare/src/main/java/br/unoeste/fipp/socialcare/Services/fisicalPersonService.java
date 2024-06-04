package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.FisicalPerson;
import br.unoeste.fipp.socialcare.DataBase.entities.User;
import br.unoeste.fipp.socialcare.DataBase.repositories.fisicalPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class fisicalPersonService {
    @Autowired
    private fisicalPersonRepository fisRepo;

    public FisicalPerson addFisicalPerson (FisicalPerson fisicalPerson) {
        if (fisRepo.existsByCpf(fisicalPerson.getCpf())) {
            throw new IllegalArgumentException("Cpf already exists");
        }
        return fisRepo.save(fisicalPerson);
    }

    public boolean deleteById (Long id) {
        try{
            fisRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean existsByCpf(String cpf) {
        return fisRepo.existsByCpf(cpf);
    }
    public boolean deleteByCpf(String cpf) {
        try {
            fisRepo.deleteByCpf(cpf);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public FisicalPerson getById (Long id) {
        FisicalPerson fisicalPerson = fisRepo.findById(id).get();
        return fisicalPerson;
    }
    public FisicalPerson getByCpf(String Cpf) {
        return fisRepo.findByCpf(Cpf);
    }

    public List<FisicalPerson> getAll (){
        return fisRepo.findAll();
    }
}

