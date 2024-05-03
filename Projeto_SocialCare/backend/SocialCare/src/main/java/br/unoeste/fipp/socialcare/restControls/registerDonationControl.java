package br.unoeste.fipp.socialcare.restControls;
import br.unoeste.fipp.socialcare.db.entities.CategoryProduct;
import br.unoeste.fipp.socialcare.db.entities.Giver;
import br.unoeste.fipp.socialcare.db.entities.Person;
import br.unoeste.fipp.socialcare.db.entities.Product;
import br.unoeste.fipp.socialcare.services.*;
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
@RequestMapping(value="apis/register-donation/")
public class registerDonationControl {

    /////////////////////////////////////////////////

    // -- Entidades que esse control deve utilizar:
    // - CategoryProduct
    // - Giver
    // - Person
    // - Product
    // - Storage

    /////////////////////////////////////////////////

    @Autowired
    private categoryProductService catService;

    @Autowired
    private giverService giverService;

    @Autowired
    private personService pesService;

    @Autowired
    private productService proService;

    @Autowired
    private storageService stoService;

    @GetMapping(value="connection-test")
    public String connectionTest(){
        return "connected";
    }


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


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////// CRUD for Givers ///////////////////////////////////////////

    @GetMapping("/delete-giver")
    public ResponseEntity<Object> deleteGiver(@RequestParam(value="pessoa_pes_id") Long pes_id)
    {
        if(giverService.deleteById(pes_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/add-giver")
    public ResponseEntity<Object> addGiver (@RequestBody Giver giver)
    {
        return new ResponseEntity<>(giverService.addGiver(giver), HttpStatus.OK);
    }
    @GetMapping("/get-giver")
    public ResponseEntity<Object> getGiver(@RequestParam(value="pessoa_pes_id") Long pes_id)
    {
        return new ResponseEntity<>(giverService.getById(pes_id),HttpStatus.OK);
    }
    @GetMapping("/get-all-givers")
    public ResponseEntity<Object> getAllGivers()
    {
        return new ResponseEntity<>(giverService.getAll(),HttpStatus.OK);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// CRUD for Personas /////////////////////////////////////////

    @GetMapping("/delete-person")
    public ResponseEntity<Object> deletePerson(@RequestParam(value="pes_id") Long pes_id)
    {
        if(pesService.deleteById(pes_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/add-person")
    public ResponseEntity<Object> addPerson (@RequestBody Person person)
    {
        return new ResponseEntity<>(pesService.addPerson(person), HttpStatus.OK);
    }
    @GetMapping("/get-person")
    public ResponseEntity<Object> getPerson(@RequestParam(value="pes_id") Long pes_id)
    {
        return new ResponseEntity<>(pesService.getById(pes_id),HttpStatus.OK);
    }
    @GetMapping("/get-all-personas")
    public ResponseEntity<Object> getAllPersonas()
    {
        return new ResponseEntity<>(pesService.getAll(),HttpStatus.OK);
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

    @GetMapping("/delete-product")
    public ResponseEntity<Object> deleteStorage(@RequestParam(value="pro_id") Long pro_id)
    {
        if(proService.deleteById(pro_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/add-product")
    public ResponseEntity<Object> insertStorage(@RequestBody Product product)
    {
        return new ResponseEntity<>(proService.addProduct(product), HttpStatus.OK);
    }
    @GetMapping("/get-product")
    public ResponseEntity<Object> getStorage(@RequestParam(value="pro_id") Long pro_id)
    {
        return new ResponseEntity<>(proService.getById(pro_id),HttpStatus.OK);
    }
    @GetMapping("/get-all-products")
    public ResponseEntity<Object> getAllStorages()
    {
        return new ResponseEntity<>(proService.getAll(),HttpStatus.OK);
    }



}
