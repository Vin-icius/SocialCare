package br.unoeste.fipp.socialcare.RestControls.useCases;
import br.unoeste.fipp.socialcare.DataBase.entities.*;
import br.unoeste.fipp.socialcare.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
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



    /*
    @PostMapping("register-donation2")
    public ResponseEntity<Object> registerDonation(@RequestParam String donor,
                                                   @RequestParam String obs,
                                                   @RequestParam String donationData,
                                                   @RequestParam String unidade,
                                                   @RequestParam List<Map<String, String>> products) {

        System.out.println("teste");
        try {
            // Buscar a pessoa física pelo CPF
            FisicalPerson pessoa = fpService.getByCpf(donor);
            if (pessoa == null) {
                pessoa = new FisicalPerson();
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate data2 = LocalDate.parse(donationData, formatter);
            donation.setData(data2);
            donation.setPessoa(pessoa);
            donation.setObservacao(obs);
            donationService.addDonation(donation);

            // Processar cada produto na lista
            for (Map<String, String> productData : products){
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

                Storage estoque = stService.getByUnity(unity);
                estoque.setEst_quantidade(estoque.getEst_quantidade()+qtde1);
                stService.addStorage(estoque);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao registrar doação: " + e.getMessage());
        }
        return ResponseEntity.ok("Registrado com sucesso");
    }*/

    @PostMapping("register-donation3")
    public ResponseEntity<Object> registerDonation(@RequestParam String donor,
                                                   @RequestParam String obs,
                                                   @RequestParam String donationData,
                                                   @RequestParam String unidade,
                                                   @RequestBody List<ProductDTO> products) {


        System.out.println("CPF "+ donor);
        donor = donor.replaceAll("[^0-9]", "");
        System.out.println("CPF "+ donor);

        System.out.println("Unidade "+ unidade);

        try {

            for (ProductDTO productData : products) {
                System.out.println("Qtde "+productData.getQtde()+" "+"Cat id "+productData.getCategory()+" "+"Prod id "+productData.getProduct());
            }

            FisicalPerson pessoa = fpService.getByCpf(donor);
            if(pessoa == null) {
                pessoa = fpService.getById(1L);
            }

            Long uni1 = Long.parseLong(unidade);
            Unity unity = uService.getById(uni1);
            if (unity == null) {
                return ResponseEntity.badRequest().body("Unidade não encontrada para o ID: " + unidade);
            }

            // Atualizar a tabela de Doação
            Donation donation = new Donation();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate data2 = LocalDate.parse(donationData, formatter);

            donation.setData(data2);
            donation.setPessoa(pessoa);
            donation.setObservacao(obs);

            donationService.addDonation(donation);
            System.out.println("chegou");

            for (ProductDTO productData : products) {
                Long catProdId = productData.getCategory();
                CategoryProduct catprod = cpService.getById(catProdId);
                if (catprod == null) {
                    return ResponseEntity.badRequest().body("Categoria de produtos não encontrada para o ID: " + catProdId);
                }

                Long prodId = productData.getProduct();
                Product produto = pdService.getById(prodId);
                if (produto == null) {
                    return ResponseEntity.badRequest().body("Produto não encontrado para o ID: " + prodId);
                }

                int quantity = productData.getQtde();

                // Atualizar a tabela de Itens da doação
                DonationItens dItens = new DonationItens();
                dItens.setQuantidade(quantity);
                ditService.addDonation(dItens);

                Storage estoque = stService.getByUnity(unity);
                estoque.setEst_quantidade(estoque.getEst_quantidade() + quantity);
                stService.addStorage(estoque);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao registrar doação: " + e.getMessage());
        }
        return ResponseEntity.ok("Registrado com sucesso");
    }


    /*
    @PostMapping("register-donation2")
    public ResponseEntity<Object> registerDonation(@RequestParam String donor,
                                                   @RequestParam String obs,
                                                   @RequestParam String donationData,
                                                   @RequestParam String unidade,
                                                   @RequestBody List<ProductDTO> products) {

        System.out.println("teste");
        try {
            // Buscar a pessoa física pelo CPF
            FisicalPerson pessoa = fpService.getByCpf(donor);
            if (pessoa == null) {
                pessoa = new FisicalPerson();
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate data2 = LocalDate.parse(donationData, formatter);
            donation.setData(data2);
            donation.setPessoa(pessoa);
            donation.setObservacao(obs);
            donationService.addDonation(donation);

            for (ProductDTO productData : products) {
                Long catProdId = productData.getCat_id();
                CategoryProduct catprod = cpService.getById(catProdId);
                if (catprod == null) {
                    return ResponseEntity.badRequest().body("Categoria de produtos não encontrada para o ID: " + catProdId);
                }

                Long prodId = productData.getProd_id();
                Product produto = pdService.getById(prodId);
                if (produto == null) {
                    return ResponseEntity.badRequest().body("Produto não encontrado para o ID: " + prodId);
                }

                int quantity = productData.getQtde();

                // Atualizar a tabela de Itens da doação
                DonationItens dItens = new DonationItens();
                dItens.setQuantidade(quantity);
                ditService.addDonation(dItens);

                Storage estoque = stService.getByUnity(unity);
                estoque.setEst_quantidade(estoque.getEst_quantidade() + quantity);
                stService.addStorage(estoque);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao registrar doação: " + e.getMessage());
        }
        return ResponseEntity.ok("Registrado com sucesso");
    }  */
    @GetMapping("delete-donation")
    public ResponseEntity<Object> deleteDonation (@RequestParam(value="id") Long doa_id) {
        if(donationService.deleteById(doa_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("get-donation")
    public ResponseEntity<Object> getDonation (@RequestParam(value="doa_id") Long doa_id) {
        return new ResponseEntity<>(donationService.getById(doa_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-donations")
    public ResponseEntity<Object> getAllDonations() {
        return new ResponseEntity<>(donationService.getAll(),HttpStatus.OK);
    }

    @GetMapping("get-all-categories-product")
    public ResponseEntity<Object> getAllCategoriesProduct() {
        return new ResponseEntity<>(cpService.getAll(),HttpStatus.OK);
    }

    @GetMapping("get-all-products")
    public ResponseEntity<Object> getAllProducts() {
        return new ResponseEntity<>(pdService.getAll(),HttpStatus.OK);
    }

    @GetMapping("get-by-cpf")
    public ResponseEntity<Object> getCPF(@RequestParam("cpf") String CPF) {
        return new ResponseEntity<>(fpService.getByCpf(CPF),HttpStatus.OK);
    }


    @GetMapping("get-all-unities")
    public ResponseEntity<Object> getAllUnities() {
        //System.out.println("chegou no back");
        return new ResponseEntity<>(uService.getAll(), HttpStatus.OK);
    }
}
