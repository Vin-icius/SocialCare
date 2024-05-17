package br.unoeste.fipp.socialcare.Services;


import br.unoeste.fipp.socialcare.DataBase.entities.City;
import br.unoeste.fipp.socialcare.DataBase.repositories.cityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cityService {
    @Autowired
    private cityRepository cityRepo;

    public City addCity (City city) {
        return cityRepo.save(city);
    }

    public boolean deleteById (Long id) {
        try{
            cityRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public City getById (Long id) {
        City city = cityRepo.findById(id).get();
        return city;
    }

    public List<City> getAll() {
        return cityRepo.findAll();
    }
}