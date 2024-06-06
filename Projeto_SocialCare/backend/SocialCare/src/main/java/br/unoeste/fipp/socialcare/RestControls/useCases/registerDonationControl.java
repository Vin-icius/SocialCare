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
    private unityService uService;
    @Autowired
    private productService pdService;
    @Autowired
    private storageService stService;
    @Autowired
    private donationItensService ditService;
    @Autowired
    private registerDonationService donationService;


    @PostMapping("/register-donation")
    public ResponseEntity<Object> registerDonation(@RequestParam String donor,
                                                   @RequestParam String category,
                                                   @RequestParam String product,
                                                   @RequestParam String qtde,
                                                   @RequestParam String obs,
                                                   @RequestParam String donationData,
                                                   @RequestParam String donationTime,
                                                   @RequestParam String unidade) {
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

            int qtde1 = Integer.parseInt(qtde);

            Long uni1 = Long.parseLong(unidade);
            Unity unity = uService.getById(uni1);

            DonationItens dItens = null;

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
                            //Atualizar a tabela de Doação
                            Donation donation = new Donation();
                            donation.setData(donationData+" "+donationTime);
                            donation.setPessoa(pessoa);
                            donation.setObservacao(obs);
                            donationService.addDonation(donation);

                            //Atualizar a tabela de Itens da doação
                            dItens.setQuantidade(qtde1);
                            ditService.addDonation(dItens);

                            //Atualizar o estoque
                            //Storage storage = null;
                            //storage = stService.getById();
                        }


        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao registrar doação" + e.getMessage());
        }
            return ResponseEntity.ok("Registrado com sucesso");

    }

    @GetMapping("/delete-donation")
    public ResponseEntity<Object> deleteDonation (@RequestParam(value="doa_id") Long doa_id) {
        if(donationService.deleteById(doa_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-donation")
    public ResponseEntity<Object> getCategoryProduct (@RequestParam(value="doa_id") Long doa_id) {
        return new ResponseEntity<>(donationService.getById(doa_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-categories-product")
    public ResponseEntity<Object> getAllCategoriesProduct() {
        return new ResponseEntity<>(donationService.getAll(),HttpStatus.OK);
    }
}
