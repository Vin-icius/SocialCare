package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cityRepository extends JpaRepository<City, Long> {
    City findByNome(String nome);
}
