package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.CategoryProduct;
import br.unoeste.fipp.socialcare.DataBase.repositories.categoryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryProductService {
    @Autowired
    private categoryProductRepository categoryProductRepo;

    public CategoryProduct addCategoryProduct (CategoryProduct categoryProduct) {
        return categoryProductRepo.save(categoryProduct);
    }

    public boolean deleteById (Long id) {
        try{
            categoryProductRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public CategoryProduct getById (Long id) {
        CategoryProduct categoryProduct = categoryProductRepo.findById(id).get();
        return categoryProduct;
    }

    public List<CategoryProduct> getAll() {
        return categoryProductRepo.findAll();
    }
    public boolean findByIdB(Long id)
    {
        try{
            categoryProductRepo.findById(id);

        }catch (Exception e){
            return false;
        }
        return true;
    }
}