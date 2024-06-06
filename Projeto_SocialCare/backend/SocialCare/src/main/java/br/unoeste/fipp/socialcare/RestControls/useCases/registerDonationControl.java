package br.unoeste.fipp.socialcare.RestControls.useCases;
import br.unoeste.fipp.socialcare.DataBase.entities.City;
import br.unoeste.fipp.socialcare.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="apis/register-donation/")
public class registerDonationControl {

    /////////////////////////////////////////////////

    // -- Entidades que esse control deve utilizar:
    // - CategoryProduct
    // - Fisical Person
    // - Person
    // - Product
    // - Storage

    /////////////////////////////////////////////////

    @GetMapping(value="connection-test")
    public String connectionTest(){
        return "connected";
    }

    @Autowired
    private categoryProductService cpService;
    @Autowired
    private fisicalPersonService fpService;
    @Autowired
    private personService psService;
    @Autowired
    private productService pdService;
    @Autowired
    private storageService stService;
    @Autowired
    private cityService ctService;


    @PostMapping("/register-donation")
    public ResponseEntity<Object> registerDonation(@RequestParam String cat_id,
                                                   @RequestParam String pro_id,
                                                   @RequestParam String pesf_id,
                                                   @RequestParam String cid_id) {
        try {
            paramService.editParam(param);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao registrar doação" + e.getMessage());
        }
            return ResponseEntity.ok("Registrado com sucesso");

    }

    @GetMapping("/verify-param")

    public ResponseEntity<Object> verifyParam(){
        boolean flag = paramService.getByIdB(1L);
        if(flag)
            return  ResponseEntity.ok("Parametrização existe!");
        else
            return ResponseEntity.badRequest().body("Parametrização não existe.");
    }



}
