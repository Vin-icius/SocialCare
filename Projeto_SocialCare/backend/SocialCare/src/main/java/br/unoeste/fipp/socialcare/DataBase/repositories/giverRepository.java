package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.FisicalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface giverRepository extends JpaRepository<FisicalPerson, Long> {
}