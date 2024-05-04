package br.unoeste.fipp.socialcare.RestControls;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="apis/citizen/")
public class userRestControl {

    @GetMapping(value="connection-test")
    public String connectionTest(){
        return "connected";
    }


}