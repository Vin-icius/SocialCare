package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.FisicalPerson;
import br.unoeste.fipp.socialcare.DataBase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface fisicalPersonRepository extends JpaRepository<FisicalPerson, Long> {
    boolean existsByCpf(String Cpf);
    FisicalPerson findByCpf(String Cpf);
    void deleteByCpf(String cpf);
}
