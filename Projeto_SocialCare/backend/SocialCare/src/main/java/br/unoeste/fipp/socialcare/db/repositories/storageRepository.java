package br.unoeste.fipp.socialcare.db.repositories;

import br.unoeste.fipp.socialcare.db.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface storageRepository extends JpaRepository<Storage, Long> {

}
