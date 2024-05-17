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
    //Pessoas
    @Autowired
    private personService perService;

    @PostMapping("/add-person")
    public ResponseEntity<Object> addPerson (@RequestBody Person person) {
        return new ResponseEntity<>(perService.addPerson(person), HttpStatus.OK);
    }

    @GetMapping("/delete-person")
    public ResponseEntity<Object> deletePerson (@RequestParam(value="pes_id") Long pes_id) {
        if(perService.deleteById(pes_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-person")
    public ResponseEntity<Object> getPerson (@RequestParam(value="pes_id") Long pes_id) {
        return new ResponseEntity<>(perService.getById(pes_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-persons")
    public ResponseEntity<Object> getAllPersons() {
        return new ResponseEntity<>(perService.getAll(),HttpStatus.OK);
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





    @Autowired
    private categoryProductService catService;


//    private FisicalPerson fis_person; tem que fazer um jeito

    @Autowired
    private productService proService;

    @Autowired
    private storageService stoService;

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

    ///////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// CRUD for Personas /////////////////////////////////////////

//    @GetMapping("/delete-person")
//    public ResponseEntity<Object> deletePerson(@RequestParam(value="pes_id") Long pes_id)
//    {
//        if(pesService.deleteById(pes_id))
//            return new ResponseEntity<>("",HttpStatus.OK);
//        else
//            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
//    }
//    @PostMapping("/add-person")
//    public ResponseEntity<Object> addPerson (@RequestBody Person person)
//    {
//        return new ResponseEntity<>(pesService.addPerson(person), HttpStatus.OK);
//    }
//    @GetMapping("/get-person")
//    public ResponseEntity<Object> getPerson(@RequestParam(value="pes_id") Long pes_id)
//    {
//        return new ResponseEntity<>(pesService.getById(pes_id),HttpStatus.OK);
//    }
//    @GetMapping("/get-all-personas")
//    public ResponseEntity<Object> getAllPersonas()
//    {
//        return new ResponseEntity<>(pesService.getAll(),HttpStatus.OK);
//    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////// CRUD for Categories of Products //////////////////////////////////////////////

    @GetMapping("/delete-category-product")
    public ResponseEntity<Object> deleteCategory(@RequestParam(value="cat_id") Long cat_id)
    {
        if(catService.deleteById(cat_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/add-category-product")
    public ResponseEntity<Object> addCategory (@RequestBody CategoryProduct category)
    {
        return new ResponseEntity<>(catService.addCategoryProduct(category), HttpStatus.OK);
    }
    @GetMapping("/get-category-product")
    public ResponseEntity<Object> getCategory(@RequestParam(value="cat_id") Long cat_id)
    {
        return new ResponseEntity<>(catService.getById(cat_id),HttpStatus.OK);
    }
    @GetMapping("/get-all-categories")
    public ResponseEntity<Object> getAllCategories()
    {
        return new ResponseEntity<>(catService.getAll(),HttpStatus.OK);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////// CRUD for Products //////////////////////////////////////////////

    @GetMapping("/delete-product")
    public ResponseEntity<Object> deleteProduct(@RequestParam(value="pro_id") Long pro_id)
    {
        if(proService.deleteById(pro_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/add-product")
    public ResponseEntity<Object> addProduct(@RequestBody Product product)
    {
        return new ResponseEntity<>(proService.addProduct(product), HttpStatus.OK);
    }
    @GetMapping("/get-product")
    public ResponseEntity<Object> getProduct(@RequestParam(value="pro_id") Long pro_id)
    {
        return new ResponseEntity<>(proService.getById(pro_id),HttpStatus.OK);
    }
    @GetMapping("/get-all-products")
    public ResponseEntity<Object> getALlProducts()
    {
        return new ResponseEntity<>(proService.getAll(),HttpStatus.OK);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////// CRUD for Storage //////////////////////////////////////////////

    @GetMapping("/delete-storage")
    public ResponseEntity<Object> deleteStorage(@RequestParam(value="pro_id") Long pro_id)
    {
        if(stoService.deleteById(pro_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/add-storage")
    public ResponseEntity<Object> insertStorage(@RequestBody Product product)
    {
        return new ResponseEntity<>(proService.addProduct(product), HttpStatus.OK);
    }
    @GetMapping("/get-storage")
    public ResponseEntity<Object> getStorage(@RequestParam(value="pro_id") Long pro_id)
    {
        return new ResponseEntity<>(proService.getById(pro_id),HttpStatus.OK);
    }
    @GetMapping("/get-all-storages")
    public ResponseEntity<Object> getAllStorages()
    {
        return new ResponseEntity<>(proService.getAll(),HttpStatus.OK);
    }
}
