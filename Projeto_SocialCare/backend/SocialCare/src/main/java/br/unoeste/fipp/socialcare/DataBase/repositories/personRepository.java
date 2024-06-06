package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface personRepository extends JpaRepository<Person, Long> {
}
