package br.unoeste.fipp.socialcare.RestControls;

import br.unoeste.fipp.socialcare.DataBase.entities.*;
import br.unoeste.fipp.socialcare.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="apis/admin/")
public class adminRestControl {
    @GetMapping(value="connection-test")
    public String connectionTest(){
        return "connected";
    }

    /////////////////////////////////////// ORDEM PARA INSTANCIAR //////////////////////////////
    //            CONTROL -> ENTITY -> SERVICES -> DAOS ( REPOSITORIES )                      //
    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////
    //  FAZER O SINGLETON NO JAVASCRIPT E NO BACKEND FAZER O SINGLETON PARA ACESSAR O BANCO  //
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    //CategoryProduct
    @Autowired
    private categoryProductService categoryProductService;

    @PostMapping("/add-category-product")
    public ResponseEntity<Object> addCategoryProduct (@RequestBody CategoryProduct categoryProduct) {
        return new ResponseEntity<>(categoryProductService.addCategoryProduct(categoryProduct), HttpStatus.OK);
    }

    @GetMapping("/delete-category-product")
    public ResponseEntity<Object> deleteCategoryProduct (@RequestParam(value="cat_id") Long cat_id) {
        if(categoryProductService.deleteById(cat_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-category-product")
    public ResponseEntity<Object> getCategoryProduct (@RequestParam(value="cat_id") Long cat_id) {
        return new ResponseEntity<>(categoryProductService.getById(cat_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-categories-product")
    public ResponseEntity<Object> getAllCategoriesProduct() {
        return new ResponseEntity<>(categoryProductService.getAll(),HttpStatus.OK);
    }
    //---

    //City
    @Autowired
    private cityService cityService;

    @PostMapping("/add-city")
    public ResponseEntity<Object> addCity (@RequestBody City city) {
        return new ResponseEntity<>(cityService.addCity(city), HttpStatus.OK);
    }

    @GetMapping("/delete-city")
    public ResponseEntity<Object> deleteCity (@RequestParam(value="cid_id") Long cid_id) {
        if(cityService.deleteById(cid_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-city")
    public ResponseEntity<Object> getCity (@RequestParam(value="cid_id") Long cid_id) {
        return new ResponseEntity<>(cityService.getById(cid_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-cities")
    public ResponseEntity<Object> getAllCities() {
        return new ResponseEntity<>(cityService.getAll(),HttpStatus.OK);
    }
    //---

    //FisicalPerson
    @Autowired
    private fisicalPersonService fisicalPersonService;

    @PostMapping("/add-fisical-person")
    public ResponseEntity<Object> addFisicalPerson (@RequestBody FisicalPerson fisicalPerson) {
        return new ResponseEntity<>(fisicalPersonService.addFisicalPerson(fisicalPerson), HttpStatus.OK);
    }

    @GetMapping("/delete-fisical-person")
    public ResponseEntity<Object> deleteFisicalPerson (@RequestParam(value="pessoa_pes_id") Long pessoa_pes_id) {
        if(fisicalPersonService.deleteById(pessoa_pes_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-fisical-person")
    public ResponseEntity<Object> getFisicalPerson (@RequestParam(value="pessoa_pes_id") Long pessoa_pes_id) {
        return new ResponseEntity<>(fisicalPersonService.getById(pessoa_pes_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-fisical-persons")
    public ResponseEntity<Object> getAllFisicalPersons() {
        return new ResponseEntity<>(fisicalPersonService.getAll(),HttpStatus.OK);
    }
    //---

    //LegalPerson
    @Autowired
    private legalPersonService legalPersonService;

    @PostMapping("/add-legal-person")
    public ResponseEntity<Object> addLegalPerson (@RequestBody LegalPerson legalPerson) {
        return new ResponseEntity<>(legalPersonService.addLegalPerson(legalPerson), HttpStatus.OK);
    }

    @GetMapping("/delete-legal-person")
    public ResponseEntity<Object> deleteLegalPerson (@RequestParam(value="pessoa_pes_id") Long pessoa_pes_id) {
        if(legalPersonService.deleteById(pessoa_pes_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-legal-person")
    public ResponseEntity<Object> getLegalPerson (@RequestParam(value="pessoa_pes_id") Long pessoa_pes_id) {
        return new ResponseEntity<>(legalPersonService.getById(pessoa_pes_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-legal-persons")
    public ResponseEntity<Object> getAllLegalPersons() {
        return new ResponseEntity<>(legalPersonService.getAll(),HttpStatus.OK);
    }
    //---

    //Pessoas
    @Autowired
    private personService personService;

    @PostMapping("/add-person")
    public ResponseEntity<Object> addPerson (@RequestBody Person person) {
        return new ResponseEntity<>(personService.addPerson(person), HttpStatus.OK);
    }

    @GetMapping("/delete-person")
    public ResponseEntity<Object> deletePerson (@RequestParam(value="pes_id") Long pes_id) {
        if(personService.deleteById(pes_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-person")
    public ResponseEntity<Object> getPerson (@RequestParam(value="pes_id") Long pes_id) {
        return new ResponseEntity<>(personService.getById(pes_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-persons")
    public ResponseEntity<Object> getAllPersons() {
        return new ResponseEntity<>(personService.getAll(),HttpStatus.OK);
    }
    //---
    
    //Product
    @Autowired
    private productService productService;

    @PostMapping("/add-product")
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
    }

    @GetMapping("/delete-product")
    public ResponseEntity<Object> deleteProduct(@RequestParam(value="pro_id") Long pro_id) {
        if(productService.deleteById(pro_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-product")
    public ResponseEntity<Object> getProduct(@RequestParam(value="pro_id") Long pro_id) {
        return new ResponseEntity<>(productService.getById(pro_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<Object> getALlProducts() {
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }
    //---

    //State
    @Autowired
    private stateService stateService;
    @PostMapping("/add-state")
    public ResponseEntity<Object> addState (@RequestBody State state) {
        return new ResponseEntity<>(stateService.addState(state), HttpStatus.OK);
    }

    @GetMapping("/delete-state")
    public ResponseEntity<Object> deleteState (@RequestParam(value="est_id") Long est_id) {
        if(stateService.deleteById(est_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-state")
    public ResponseEntity<Object> getState (@RequestParam(value="est_id") Long est_id) {
        return new ResponseEntity<>(stateService.getById(est_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-states")
    public ResponseEntity<Object> getAllStates() {
        return new ResponseEntity<>(stateService.getAll(),HttpStatus.OK);
    }
    //---

    //Storage
    @Autowired
    private storageService storageService;

    @PostMapping("/add-storage")
    public ResponseEntity<Object> addStorage(@RequestBody Storage storage) {
        return new ResponseEntity<>(storageService.addStorage(storage), HttpStatus.OK);
    }

    @GetMapping("/delete-storage")
    public ResponseEntity<Object> deleteStorage(@RequestParam(value="pro_id") Long pro_id) {
        if(storageService.deleteById(pro_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/get-storage")
    public ResponseEntity<Object> getStorage(@RequestParam(value="pro_id") Long pro_id) {
        return new ResponseEntity<>(storageService.getById(pro_id),HttpStatus.OK);
    }
    
    @GetMapping("/get-all-storages")
    public ResponseEntity<Object> getAllStorages() {
        return new ResponseEntity<>(storageService.getAll(),HttpStatus.OK);
    }
    //---

    //User
    @Autowired
    private userService userService;

    @PostMapping("/add-user")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-user")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete-user")
    public ResponseEntity<Object> deleteUser(@RequestParam(value="id") Long id) {
        try {
            if (userService.deleteById(id)) {
                return new ResponseEntity<>("", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-user")
    public ResponseEntity<Object> getUser(@RequestParam(value="pro_id") Long pro_id) {
        return new ResponseEntity<>(userService.getById(pro_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
    }
    //---
}