package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface registerDonationRepository extends JpaRepository<Donation, Long> {
}
