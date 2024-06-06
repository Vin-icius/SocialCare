package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.FisicalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface fisicalPersonRepository extends JpaRepository<FisicalPerson, Long> {
    boolean existsByCpf(String Cpf);
    FisicalPerson findByCpf(String Cpf);
    void deleteByCpf(String cpf);

}
