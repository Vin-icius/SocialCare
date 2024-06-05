package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.Actions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface actionsRepository extends JpaRepository<Actions, Long> {
}
