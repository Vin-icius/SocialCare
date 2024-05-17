package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.LegalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface legalPersonRepository extends JpaRepository<LegalPerson, Long> {
}
