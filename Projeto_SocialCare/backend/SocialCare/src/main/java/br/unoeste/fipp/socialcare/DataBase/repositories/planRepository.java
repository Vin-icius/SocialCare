package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface planRepository extends JpaRepository<Plan, Long> {
}
