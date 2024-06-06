package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.CategoryProduct;
import br.unoeste.fipp.socialcare.DataBase.entities.Compra;
import br.unoeste.fipp.socialcare.DataBase.entities.PurchasingProducts;
import br.unoeste.fipp.socialcare.DataBase.entities.Storage;
import br.unoeste.fipp.socialcare.DataBase.repositories.CompraRepository;
import br.unoeste.fipp.socialcare.DataBase.repositories.purchasingProductsRepository;
import br.unoeste.fipp.socialcare.DataBase.repositories.storageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class purchasingProductService {
    @Autowired
    private purchasingProductsRepository repo;


    public PurchasingProducts addProducts (PurchasingProducts products) {
        return repo.save(products);
    }


    public boolean deleteById (Long id) {
        try{
            repo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public PurchasingProducts getById (Long id) {
        PurchasingProducts purchasingProducts = repo.findById(id).get();
        return purchasingProducts;
    }

    public List<PurchasingProducts> getAll() {
        return repo.findAll();
    }
    public boolean findByIdB(Long id)
    {
        try{
           repo.findById(id);

        }catch (Exception e){
            return false;
        }
        return true;
    }
}
