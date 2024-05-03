package br.unoeste.fipp.socialcare.db.repositories;

import br.unoeste.fipp.socialcare.db.entities.Giver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface giverRepository extends JpaRepository<Giver, Long> {
}
