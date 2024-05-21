package br.unoeste.fipp.socialcare.RestControls.useCases;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
