package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.Gender;
import br.unoeste.fipp.socialcare.DataBase.repositories.genderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class genderService {

    @Autowired
    private genderRepository genderRepo;

    public Gender addGender (Gender gender) {
        return genderRepo.save(gender);
    }

    public boolean deleteById (Long id) {
        try{
            genderRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public Gender getById (Long id) {
        Gender gender = genderRepo.findById(id).get();
        return gender;
    }

    public List<Gender> getAll (){
        return genderRepo.findAll();
    }
}
