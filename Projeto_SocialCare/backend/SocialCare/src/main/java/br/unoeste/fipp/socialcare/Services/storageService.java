package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.Storage;
import br.unoeste.fipp.socialcare.DataBase.repositories.storageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class storageService {

    @Autowired
    private storageRepository storeRepo;

    public Storage getById(Long id){
        Storage storage = storeRepo.findById(id).get();
        return storage;
    }

    public List<Storage> getAll(){
        return storeRepo.findAll();
    }

    public Storage addStorage(Storage storage){
        return storeRepo.save(storage);
    }

    public boolean deleteById(Long id){
        try{
            storeRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
