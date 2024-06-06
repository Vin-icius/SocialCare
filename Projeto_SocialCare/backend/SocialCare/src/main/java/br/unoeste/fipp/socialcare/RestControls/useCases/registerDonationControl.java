package br.unoeste.fipp.socialcare.RestControls.useCases;
import br.unoeste.fipp.socialcare.DataBase.entities.*;
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
    @Autowired
    private registerDonationService donationService;


    @PostMapping("/register-donation")
    public ResponseEntity<Object> registerDonation(@RequestBody Donation donation) {
        try {

            // Buscar a pessoa física pelo CPF
            FisicalPerson pessoa = donation.getPessoa();
            pessoa = fpService.getByCpf(pessoa.getCpf());
            Product
            if (pessoa == null) {
                return ResponseEntity.badRequest().body("Pessoa física não encontrada para o CPF: " + pessoa.getCpf());
            }
            else
                if(!pessoa.getAtivo()){
                    return ResponseEntity.badRequest().body("Pessoa física não está ativa");
                }
                else
                    if
                    donationService.addDonation(donation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao registrar doação" + e.getMessage());
        }
            return ResponseEntity.ok("Registrado com sucesso");

    }
}
