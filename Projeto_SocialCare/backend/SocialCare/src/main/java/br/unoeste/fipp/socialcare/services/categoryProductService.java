package br.unoeste.fipp.socialcare.services;

import br.unoeste.fipp.socialcare.db.entities.CategoryProduct;
import br.unoeste.fipp.socialcare.db.repositories.categoryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryProductService {

    @Autowired
    private categoryProductRepository catProdRepo;
    public CategoryProduct getById(Long id){
        CategoryProduct cat = catProdRepo.findById(id).get();
        return cat;
    }

    public List<CategoryProduct> getAll(){
        return catProdRepo.findAll();
    }

    public CategoryProduct addCategoryProduct(CategoryProduct cat){
        return catProdRepo.save(cat);
    }

    public boolean deleteById(Long id){
        try{
            catProdRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
