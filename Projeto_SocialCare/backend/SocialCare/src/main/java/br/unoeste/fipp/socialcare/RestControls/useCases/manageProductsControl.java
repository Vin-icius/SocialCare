package br.unoeste.fipp.socialcare.RestControls.useCases;

import br.unoeste.fipp.socialcare.DataBase.entities.Product;
import br.unoeste.fipp.socialcare.Services.categoryProductService;
import br.unoeste.fipp.socialcare.Services.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="apis/citizen/manage-products/")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class manageProductsControl {
    @Autowired
    private productService proService;
    @Autowired
    private categoryProductService catproService;
    @PostMapping("/add-products")
    public ResponseEntity<Object> addProduct(@RequestParam("pro_nome") String pro_nome,
                                              @RequestParam("cat_id")Long cat_id){
        if(catproService.findByIdB(cat_id)){
            try{
                proService.addProduct(new Product(0L,pro_nome,catproService.getById(cat_id)));


            }catch (Exception e){
                return ResponseEntity.badRequest().body("Erro ao cadastrar produtos"+e.getMessage());
            }
            return ResponseEntity.ok("inserido com sucesso");
        }

       return ResponseEntity.ok("");


    }

    @PostMapping("/update-product")
    public ResponseEntity<Object> updateProduct(@RequestParam("pro_id") Long pro_id,
                                                @RequestParam("pro_nome") String pro_nome,
                                                @RequestParam("cat_id") Long cat_id) {
        if (catproService.findByIdB(cat_id)) {
            try {
                Product existingProduct = proService.getById(pro_id);
                if (existingProduct != null) {
                    existingProduct.setNome(pro_nome);
                    existingProduct.setCategoryProduct(catproService.getById(cat_id));
                    Product updatedProduct = proService.updateProduct(existingProduct);
                    if (updatedProduct != null) {
                        return ResponseEntity.ok("Produto atualizado com sucesso");
                    } else {
                        return ResponseEntity.badRequest().body("Erro ao atualizar produto");
                    }
                } else {
                    return ResponseEntity.badRequest().body("Produto não encontrado");
                }


            }catch (Exception e){
                return ResponseEntity.badRequest().body("Erro ao atualizar produto"+e.getMessage());
            }

        }
        return ResponseEntity.badRequest().body("Categoria nao encontrada");
    }
    @GetMapping("/get-product")
    public ResponseEntity<Object> getProduct(@RequestParam(value="pro_id") Long pro_id) {
        return new ResponseEntity<>(proService.getById(pro_id), HttpStatus.OK);
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<Object> getALlProducts() {
        return new ResponseEntity<>(proService.getAll(),HttpStatus.OK);
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
