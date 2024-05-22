package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.Parametrization;
import br.unoeste.fipp.socialcare.DataBase.repositories.parametrizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class parametrizationService {
    @Autowired
    private parametrizationRepository paramRepo;

    public Parametrization addParam (Parametrization param) {
        return paramRepo.save(param);
    }

    public boolean deleteById (Long id) {
        try{
            paramRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public Parametrization getById (Long id) {
        Parametrization param = paramRepo.findById(id).get();
        return param;
    }

    public boolean getByIdB(Long id){
        Parametrization param = paramRepo.findById(id).get();
        return param!=null;
    }

    public List<Parametrization> getAll() {
        return paramRepo.findAll();
    }
}
