package br.unoeste.fipp.socialcare.RestControls.useCases;

import br.unoeste.fipp.socialcare.DataBase.entities.Compra;
import br.unoeste.fipp.socialcare.DataBase.entities.LegalPerson;
import br.unoeste.fipp.socialcare.Services.compraService;
import br.unoeste.fipp.socialcare.Services.legalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apis/citizen/manage-compras/")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ItensCompraControl {
    @Autowired
    private compraService compraService;
    @Autowired
    private legalPersonService legalPersonService;
    @PostMapping("/add-compra")
    public ResponseEntity<Object> addCompra(@RequestBody Compra compra) {
        try {
            // Extrair o CNPJ da pessoa jurídica da compra
            String cnpj = compra.getLegalPerson().getCnpj();

            // Encontrar a pessoa jurídica pelo CNPJ
            LegalPerson legalPerson = legalPersonService.findByCnpj(cnpj);

            // Se a pessoa jurídica não for encontrada, retorne um erro
            if (legalPerson == null) {
                return new ResponseEntity<>("Pessoa jurídica não encontrada para o CNPJ fornecido", HttpStatus.NOT_FOUND);
            }

            // Definir a pessoa jurídica encontrada na compra
            compra.setLegalPerson(legalPerson);

            // Salvar a nova compra
            compraService.addCompra(compra);

            return new ResponseEntity<>("Compra adicionada com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete-compra")
    public ResponseEntity<Object> deleteCompra(@RequestParam(value = "com_id") Long com_id) {
        if (compraService.deleteById(com_id))
            return new ResponseEntity<>("", HttpStatus.OK);
        else
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-compra")
    public ResponseEntity<Object> getCompra(@RequestParam(value = "com_id") Long com_id) {
        return new ResponseEntity<>(compraService.getById(com_id), HttpStatus.OK);
    }

    @GetMapping("/get-all-compras")
    public ResponseEntity<Object> getAllCompras() {
        return new ResponseEntity<>(compraService.getAll(), HttpStatus.OK);
    }
}
