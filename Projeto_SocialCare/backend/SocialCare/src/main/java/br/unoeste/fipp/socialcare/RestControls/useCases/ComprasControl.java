package br.unoeste.fipp.socialcare.RestControls.useCases;

import br.unoeste.fipp.socialcare.DataBase.entities.Compra;
import br.unoeste.fipp.socialcare.DataBase.entities.PurchasingProducts;
import br.unoeste.fipp.socialcare.DataBase.entities.Storage;
import br.unoeste.fipp.socialcare.Services.compraService;
import br.unoeste.fipp.socialcare.Services.purchasingProductService;
import br.unoeste.fipp.socialcare.Services.storageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apis/citizen/manage-itens-compras/")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ComprasControl {
    @Autowired
    private purchasingProductService ppservice;
    @Autowired
    private compraService compraService;
    @Autowired
    private storageService storageService;


    @PostMapping("/add-itens-compra")
    public ResponseEntity<Object> addItensCompra(@RequestBody PurchasingProducts PurchasingProducts) {
        try {


            Compra compra = compraService.getByName(PurchasingProducts.getCompra().getDesc());

            // Buscar o produto pelo nome
            Storage product = storageService.getIdByNameB(PurchasingProducts.getProduct().getProduct().getNome());

            // Buscar a unidade pelo nome
            Storage storage = storageService.getByName(PurchasingProducts.getUni().getUnity().getNome());

            // Verificar se as entidades foram encontradas
            if (compra == null) {
                return new ResponseEntity<>("Compra não encontrada", HttpStatus.NOT_FOUND);
            }

            if (product == null) {
                return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_FOUND);
            }

            if (storage == null) {
                return new ResponseEntity<>("Unidade não encontrada", HttpStatus.NOT_FOUND);
            }


            PurchasingProducts.setCompra(compra);
            PurchasingProducts.setProduct(product);
            PurchasingProducts.setUni(storage);

            // Salvar a nova compra
            ppservice.addProducts(PurchasingProducts);

            return new ResponseEntity<>("Compra adicionada com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/delete-itens-compra")
    public ResponseEntity<Object> deleteItensCompra(@RequestParam(value = "itc_id") Long itc_id) {
        if (ppservice.deleteById(itc_id))
            return new ResponseEntity<>("", HttpStatus.OK);
        else
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/get-itens-compra")
    public ResponseEntity<Object> getItensCompra(@RequestParam(value = "itc_id") Long itc_id) {
        return new ResponseEntity<>(ppservice.getById(itc_id), HttpStatus.OK);
    }

    @GetMapping("/get-all-itens-compras")
    public ResponseEntity<Object> getAllItensCompras() {
        return new ResponseEntity<>(ppservice.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-produtos-estoque")
    public ResponseEntity<Object> getAllItensEstoque() {
        return new ResponseEntity<>(storageService.getProdutosNoEstoque(), HttpStatus.OK);
    }
    @GetMapping("/get-unidades-estoque")
    public ResponseEntity<Object> getAllUnidadesEstoque() {
        return new ResponseEntity<>(storageService.getUnidadesNoEstoque(), HttpStatus.OK);
    }

    @GetMapping("/get-all-compras")
    public ResponseEntity<Object> getAllCompras() {
        return new ResponseEntity<>(compraService.getAll(), HttpStatus.OK);
    }
}

