package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.FisicalPerson;
import br.unoeste.fipp.socialcare.DataBase.entities.User;
import br.unoeste.fipp.socialcare.DataBase.repositories.fisicalPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public boolean existsByCpf(String cpf) {
        return fisRepo.existsByCpf(cpf);
    }

    public Long getIdByCpf(String cpf) {
        FisicalPerson fisicalPerson = fisRepo.findByCpf(cpf);
        return (fisicalPerson != null) ? fisicalPerson.getId() : null;
    }

    public boolean deleteById(Long id) {
        if (!fisRepo.existsById(id)) {
            return false;
        }
        try {
            fisRepo.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
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


    public FisicalPerson updateFisicalPerson(FisicalPerson fisicalPerson) {
        // Certificando-se de que a pessoa física existe antes de tentar atualizá-la
        if (fisRepo.existsById(fisicalPerson.getId())) {
            return fisRepo.save(fisicalPerson);
        } else {
            throw new IllegalArgumentException("Pessoa física não encontrada para atualização.");
        }
    }


}

