package br.unoeste.fipp.socialcare.db.repositories;

import br.unoeste.fipp.socialcare.db.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface personRepository extends JpaRepository<Person, Long> {
}
