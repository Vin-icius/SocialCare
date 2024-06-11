package br.unoeste.fipp.socialcare.RestControls.useCases;
import br.unoeste.fipp.socialcare.DataBase.entities.*;
import br.unoeste.fipp.socialcare.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="apis/register-donation")
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


    @CrossOrigin(origins = "*")
    @PostMapping("/register-donation")
    public ResponseEntity<Object> registerDonation(@RequestParam String donor,
                                                   @RequestParam String obs,
                                                   @RequestParam String donationData,
                                                   @RequestParam String donationTime,
                                                   @RequestParam String unidade,
                                                   @RequestBody List<Map<String, String>> products) {
        try {
            // Buscar a pessoa física pelo CPF
            FisicalPerson pessoa = fpService.getByCpf(donor);
            if (pessoa == null) {
                return ResponseEntity.badRequest().body("Pessoa física não encontrada para o CPF: " + donor);
            }
            if (!pessoa.getAtivo()) {
                return ResponseEntity.badRequest().body("Pessoa física não está ativa");
            }

            Long uni1 = Long.parseLong(unidade);
            Unity unity = uService.getById(uni1);
            if (unity == null) {
                return ResponseEntity.badRequest().body("Unidade não encontrada para o ID: " + unidade);
            }

            // Atualizar a tabela de Doação
            Donation donation = new Donation();
            donation.setData(donationData + " " + donationTime);
            donation.setPessoa(pessoa);
            donation.setObservacao(obs);
            donationService.addDonation(donation);

            // Processar cada produto na lista
            for (Map<String, String> productData : products) {
                Long cat_prod1 = Long.parseLong(productData.get("category"));
                CategoryProduct catprod = cpService.getById(cat_prod1);
                if (catprod == null) {
                    return ResponseEntity.badRequest().body("Categoria de produtos não encontrada para o ID: " + cat_prod1);
                }

                Long prod1 = Long.parseLong(productData.get("product"));
                Product produto = pdService.getById(prod1);
                if (produto == null) {
                    return ResponseEntity.badRequest().body("Produto não encontrado para o ID: " + prod1);
                }

                int qtde1 = Integer.parseInt(productData.get("qtde"));

                // Atualizar a tabela de Itens da doação
                DonationItens dItens = new DonationItens();
                dItens.setQuantidade(qtde1);
                ditService.addDonation(dItens);

                // Atualizar o estoque
                stService.insertInto(unity.getId(),produto.getId(),qtde1);
            }



        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao registrar doação: " + e.getMessage());
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
