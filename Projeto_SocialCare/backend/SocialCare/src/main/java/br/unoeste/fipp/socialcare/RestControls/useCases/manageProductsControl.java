package br.unoeste.fipp.socialcare.RestControls.useCases;

import br.unoeste.fipp.socialcare.DataBase.entities.Product;
import br.unoeste.fipp.socialcare.Services.categoryProductService;
import br.unoeste.fipp.socialcare.Services.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="apis/manage-products/")
public class manageProductsControl {
    @Autowired
    private productService proService;
    @Autowired
    private categoryProductService catproService;
    @PostMapping("/add-products")
    public ResponseEntity<Object> addProduct(@RequestParam("pro_id")Long pro_id,
                                              @RequestParam("pro_nome") String pro_nome,
                                              @RequestParam("cat_id")Long cat_id){
        if(catproService.findByIdB(cat_id)){
            try{
                proService.addProduct(new Product(pro_id,pro_nome,catproService.getById(cat_id)));


            }catch (Exception e){
                return ResponseEntity.badRequest().body("Erro ao cadastrar produtos"+e.getMessage());
            }
            return ResponseEntity.ok("inserido com sucesso");
        }

       return ResponseEntity.ok("");


    }

    @GetMapping("/delete-product")
    public ResponseEntity<Object> deleteProduct(@RequestParam("pro_id")Long pro_id)
    {
        if(proService.deleteById(pro_id))
            return ResponseEntity.ok("Produto excluido com sucesso!");
        else
            return ResponseEntity.badRequest().body("Erro ao excluir produto!");
    }
}
