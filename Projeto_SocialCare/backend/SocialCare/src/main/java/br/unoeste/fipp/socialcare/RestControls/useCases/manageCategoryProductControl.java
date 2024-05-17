package br.unoeste.fipp.socialcare.RestControls.useCases;

import br.unoeste.fipp.socialcare.DataBase.entities.CategoryProduct;
import br.unoeste.fipp.socialcare.Services.categoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("apis/manage-category-products/")
public class manageCategoryProductControl {
    @Autowired
    private categoryProductService catproService;

    @RequestMapping("/add-category-product")
    public ResponseEntity<Object> addCategoryProduct(@RequestParam("cat_id")Long cat_id,
                                                     @RequestParam("cat_nome")String cat_nome){
        try{
            catproService.addCategoryProduct(new CategoryProduct(cat_id,cat_nome));

        }catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Erro ao cadastrar categoria de produtos"+e.getMessage());
        }
        return ResponseEntity.ok("");
    }

}
