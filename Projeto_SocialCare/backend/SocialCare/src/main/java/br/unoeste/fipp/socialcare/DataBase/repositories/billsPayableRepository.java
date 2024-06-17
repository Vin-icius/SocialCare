package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.BillsPayable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface billsPayableRepository extends JpaRepository<BillsPayable, Long> {

}
