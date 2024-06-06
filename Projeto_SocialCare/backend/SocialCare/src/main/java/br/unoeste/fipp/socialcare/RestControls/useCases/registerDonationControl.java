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
    public ResponseEntity<Object> registerDonation(@RequestParam String donor,
                                                   @RequestParam String category,
                                                   @RequestParam String product,
                                                   @RequestParam String obs,
                                                   @RequestParam String donationData,
                                                   @RequestParam String donationTime) {
        try {

            // Buscar a pessoa física pelo CPF
            FisicalPerson pessoa;
            pessoa = fpService.getByCpf(donor);

            Long cat_prod1 = Long.parseLong(category);
            CategoryProduct catprod;
            catprod = cpService.getById(cat_prod1);

            Long prod1 = Long.parseLong(product);
            Product produto;
            produto = pdService.getById(prod1);

            if (pessoa == null) {
                return ResponseEntity.badRequest().body("Pessoa física não encontrada para o CPF: " + pessoa.getCpf());
            }
            else
                if(!pessoa.getAtivo()){
                    return ResponseEntity.badRequest().body("Pessoa física não está ativa");
                }
                else
                    if(catprod == null)
                        return ResponseEntity.badRequest().body("Categoria de produtos não encontrada para o ID: " + catprod.getId());
                    else
                        if(product == null)
                            return ResponseEntity.badRequest().body("Produto não encontrado para o ID: " + produto.getId());
                        else{
                            Donation donation = new Donation();
                            donation.setData(donationData+" "+donationTime);
                            donation.setPessoa(pessoa);
                            donation.setObservacao(obs);
                            donationService.addDonation(donation);
                        }


        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao registrar doação" + e.getMessage());
        }
            return ResponseEntity.ok("Registrado com sucesso");

    }
}
