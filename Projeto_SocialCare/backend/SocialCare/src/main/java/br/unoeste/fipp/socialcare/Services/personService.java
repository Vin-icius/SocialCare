package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.Person;
import br.unoeste.fipp.socialcare.DataBase.repositories.personRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class personService {
    @Autowired
    private personRepository personRepo;
    public Person getById(Long id){
        Person person = personRepo.findById(id).get();
        return person;
    }

    public List<Person> getAll(){
        return personRepo.findAll();
    }

    public Person addPerson(Person user){
        return personRepo.save(user);
    }

    public boolean deleteById(Long id){
        try{
            personRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}