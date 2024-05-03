package br.unoeste.fipp.socialcare.services;

import br.unoeste.fipp.socialcare.db.entities.Product;
import br.unoeste.fipp.socialcare.db.repositories.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {

    @Autowired
    private productRepository prodRepo;

    public Product getById(Long id){
        Product product = prodRepo.findById(id).get();
        return product;
    }

    public List<Product> getAll(){
        return prodRepo.findAll();
    }

    public Product addProduct(Product prod){
        return prodRepo.save(prod);
    }

    public boolean deleteById(Long id){
        try{
            prodRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
