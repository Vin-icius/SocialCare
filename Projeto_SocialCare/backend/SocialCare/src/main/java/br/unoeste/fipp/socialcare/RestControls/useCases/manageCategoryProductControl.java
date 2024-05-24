package br.unoeste.fipp.socialcare.RestControls.useCases;

import br.unoeste.fipp.socialcare.DataBase.entities.CategoryProduct;
import br.unoeste.fipp.socialcare.Services.categoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apis/citizen/manage-category-products/")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class manageCategoryProductControl {
    @Autowired
    private categoryProductService catproService;

    @PostMapping("/add-category-product")
    public ResponseEntity<Object> addCategoryProduct(@RequestParam("cat_nome")String cat_nome){
        try{
            catproService.addCategoryProduct(new CategoryProduct(0L,cat_nome));

        }catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Erro ao cadastrar categoria de produtos"+e.getMessage());
        }
        return ResponseEntity.ok("Produto inserido");
    }
    @GetMapping("/get-category-product")
    public ResponseEntity<Object> getCategoryProduct (@RequestParam(value="cat_id") Long cat_id) {
        return new ResponseEntity<>(catproService.getById(cat_id), HttpStatus.OK);
    }

    @GetMapping("/get-all-categories-product")
    public ResponseEntity<Object> getAllCategoriesProduct() {
        return new ResponseEntity<>(catproService.getAll(),HttpStatus.OK);
    }
}
