package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.DonationItens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface donationItensRepository extends JpaRepository<DonationItens, Long> {
}
