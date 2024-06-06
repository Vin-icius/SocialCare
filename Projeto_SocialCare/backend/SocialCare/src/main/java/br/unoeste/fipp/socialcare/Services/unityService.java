package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.Unity;
import br.unoeste.fipp.socialcare.DataBase.repositories.unityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class unityService {
    @Autowired
    private unityRepository uniRepo;

    public Unity addUnity (Unity unity) {
        return uniRepo.save(unity);
    }

    public Unity getById (Long id) {
        Unity unity = uniRepo.findById(id).get();
        return unity;
    }

    public boolean getByIdB(Long id){
        Unity unity = uniRepo.findById(id).get();
        return unity!=null;
    }

    public List<Unity> getAll() {
        return uniRepo.findAll();
    }
}
