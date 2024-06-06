package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.Compra;
import br.unoeste.fipp.socialcare.DataBase.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface storageRepository extends JpaRepository<Storage, Long> {
    public Storage findByProductName(String name);
}
