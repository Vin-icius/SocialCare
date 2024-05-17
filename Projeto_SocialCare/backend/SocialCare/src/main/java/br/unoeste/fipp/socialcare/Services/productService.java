package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.Product;
import br.unoeste.fipp.socialcare.DataBase.repositories.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {
    @Autowired
    private productRepository productRepo;

    public Product addProduct (Product product) {
        return productRepo.save(product);
    }

    public boolean deleteById (Long id) {
        try{
            productRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public Product getById (Long id) {
        Product product = productRepo.findById(id).get();
        return product;
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }
}