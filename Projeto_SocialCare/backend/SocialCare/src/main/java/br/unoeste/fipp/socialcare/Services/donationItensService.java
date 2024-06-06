package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.DonationItens;
import br.unoeste.fipp.socialcare.DataBase.repositories.donationItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class donationItensService {
    @Autowired
    private donationItensRepository donationRepo;

    public DonationItens addDonation (DonationItens donation) {
        return donationRepo.save(donation);
    }

    public DonationItens getById (Long id) {
        DonationItens donation = donationRepo.findById(id).get();
        return donation;
    }

    public boolean getByIdB(Long id){
        DonationItens donation = donationRepo.findById(id).get();
        return donation!=null;
    }

    public List<DonationItens> getAll() {
        return donationRepo.findAll();
    }
}