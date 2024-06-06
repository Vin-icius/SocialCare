package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface actionRepository extends JpaRepository<Action, Long> {
}
