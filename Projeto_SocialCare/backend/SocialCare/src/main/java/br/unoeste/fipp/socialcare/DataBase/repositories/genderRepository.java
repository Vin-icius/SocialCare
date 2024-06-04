package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface genderRepository extends JpaRepository<Gender, Long> {

}
