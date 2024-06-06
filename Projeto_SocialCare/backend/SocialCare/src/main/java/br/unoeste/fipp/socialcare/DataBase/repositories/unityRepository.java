package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.Unity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface unityRepository  extends JpaRepository<Unity, Long> {
}
