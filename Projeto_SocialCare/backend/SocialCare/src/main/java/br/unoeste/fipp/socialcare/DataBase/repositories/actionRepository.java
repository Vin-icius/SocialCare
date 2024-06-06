package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.Action;
import br.unoeste.fipp.socialcare.DataBase.entities.FisicalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface actionRepository extends JpaRepository<Action, Long> {
    //List<Action> findByPessoa(FisicalPerson pessoa);
    Action findByPessoa(FisicalPerson pessoa);
    // Método para verificar se uma pessoa tem ações ativas
    //boolean existsByPessoaAndAtivo(FisicalPerson pessoa, boolean ativo);
}
