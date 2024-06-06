package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.Parametrization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface parametrizationRepository extends JpaRepository<Parametrization, Long> {
}
