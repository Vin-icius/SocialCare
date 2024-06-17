package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.FisicalPerson;
import br.unoeste.fipp.socialcare.DataBase.entities.Storage;
import br.unoeste.fipp.socialcare.DataBase.entities.Unity;
import br.unoeste.fipp.socialcare.DataBase.repositories.storageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class storageService {
    @Autowired
    private storageRepository storageRepo;

    public Storage addStorage (Storage storage) {
        return storageRepo.save(storage);
    }

    public void insertInto(Long unidade_uni_id, Long produto_pro_id, int est_quantidade){
        storageRepo.insertInStorage(unidade_uni_id,produto_pro_id,est_quantidade);
    }
    public Storage getByUnity(Unity uni){
        return storageRepo.getByUnity(uni);
    }

    public boolean deleteById (Long id) {
        try{
            storageRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public Storage getById (Long id) {
        return storageRepo.findById(id).get();
    }

    public List<Storage> getAll() {
        return storageRepo.findAll();
    }
}