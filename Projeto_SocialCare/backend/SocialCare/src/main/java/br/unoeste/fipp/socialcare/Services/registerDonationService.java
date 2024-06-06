package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.Donation;
import br.unoeste.fipp.socialcare.DataBase.repositories.registerDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ResourceLoader;

import java.util.List;

@Service
public class registerDonationService {
    @Autowired
    private registerDonationRepository donationRepo;
    @Autowired
    private ResourceLoader resourceLoader;

    public Donation addDonation (Donation donation) {
        return donationRepo.save(donation);
    }

    public Donation getById (Long id) {
        Donation donation = donationRepo.findById(id).get();
        return donation;
    }

    public boolean getByIdB(Long id){
        Donation donation = donationRepo.findById(id).get();
        return donation!=null;
    }

    public List<Donation> getAll() {
        return donationRepo.findAll();
    }
}
