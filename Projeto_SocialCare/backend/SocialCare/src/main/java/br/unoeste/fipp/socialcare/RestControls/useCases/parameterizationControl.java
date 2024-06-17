package br.unoeste.fipp.socialcare.RestControls.useCases;

import br.unoeste.fipp.socialcare.DataBase.entities.City;
import br.unoeste.fipp.socialcare.DataBase.entities.Parametrization;
import br.unoeste.fipp.socialcare.Services.cityService;
import br.unoeste.fipp.socialcare.Services.parametrizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="apis/parametrization/")
public class parameterizationControl {
    @Autowired
    private parametrizationService paramService;
    @Autowired
    private cityService ctService;
    @PostMapping("/add-param")
    public ResponseEntity<Object> addParam(@RequestParam("par_fantasia") String par_fantasia,
                                           @RequestParam("par_razao")String par_razao,
                                           @RequestParam("par_cnpj")String par_cnpj,
                                           @RequestParam("par_logradouro")String par_logradouro,
                                           @RequestParam("par_bairro")String par_bairro,
                                           @RequestParam("par_cep")String par_cep,
                                           @RequestParam("cidade_cid_id")String cidade_cid_id,
                                           @RequestParam("par_email")String par_email,
                                           @RequestParam("par_site")String par_site,
                                           @RequestParam("par_logogrande") MultipartFile par_logogrande,
                                           @RequestParam("par_logopequena")MultipartFile par_logopequena){
        Long cidade_cid_id1 = Long.parseLong(cidade_cid_id);
            try{
                City city;
                city = ctService.getById(cidade_cid_id1);

                Parametrization param = new Parametrization(par_fantasia,par_razao,par_cnpj,par_logradouro,par_bairro,par_cep,par_email,
                        par_site,"","",city);

                paramService.addParam(param,par_logogrande,par_logopequena);

            }catch (Exception e){
                return ResponseEntity.badRequest().body("Erro ao inserir parametrização"+e.getMessage());
            }
            return ResponseEntity.ok("inserido com sucesso");
    }
    @GetMapping("/delete-param")
    public ResponseEntity<Object> deleteParam(@RequestParam("par_id")Long par_id)
    {
        if(paramService.deleteById(par_id))
            return ResponseEntity.ok("Parametrização excluida com sucesso!");
        else
            return ResponseEntity.badRequest().body("Erro ao excluir parametrização!");
    }

    @GetMapping("/get-param")
    public ResponseEntity<Object> getParam()
    {
        return new ResponseEntity<>(paramService.getParam(), HttpStatus.OK);
    }

    @PostMapping("/edit-param")
    public ResponseEntity<Object> editParam(@RequestBody Parametrization param){
        if(param!=null){
            try{
                paramService.editParam(param);

            }catch (Exception e){
                return ResponseEntity.badRequest().body("Erro ao modificar parametrização"+e.getMessage());
            }
            return ResponseEntity.ok("Modificado com sucesso");
        }

        return ResponseEntity.ok("");
    }

    @GetMapping("/verify-param")

    public ResponseEntity<Object> verifyParam(){
        boolean flag = paramService.getByIdB(1L);
        if(flag)
            return  ResponseEntity.ok("Parametrização existe!");
        else
            return ResponseEntity.badRequest().body("Parametrização não existe.");
    }
}
