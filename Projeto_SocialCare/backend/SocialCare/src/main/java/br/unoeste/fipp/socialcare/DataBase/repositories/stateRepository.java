package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface stateRepository extends JpaRepository<State, Long> {

}
