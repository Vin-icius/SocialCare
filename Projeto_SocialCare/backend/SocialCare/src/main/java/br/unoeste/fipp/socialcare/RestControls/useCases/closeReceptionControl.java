package br.unoeste.fipp.socialcare.RestControls.useCases;

import br.unoeste.fipp.socialcare.DataBase.entities.*;
import br.unoeste.fipp.socialcare.Services.actionService;
import br.unoeste.fipp.socialcare.Services.fisicalPersonService;
import br.unoeste.fipp.socialcare.Services.planService;
import br.unoeste.fipp.socialcare.Services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apis/CloseReception")
public class closeReceptionControl {



    @Autowired
    private actionService actServ;

    @Autowired
    private fisicalPersonService pesfServ;


    @PostMapping("/add-action")
    public ResponseEntity<Object> addAction(@RequestParam("cpf") String cpf,
                                            @RequestParam("dtacao") String dtacao,
                                            @RequestParam("descricao") String descricao) {
        try {

            // Buscar a pessoa física pelo CPF
            FisicalPerson pessoa = pesfServ.getByCpf(cpf);
            if (pessoa == null) {
                return ResponseEntity.badRequest().body("Pessoa física não encontrada para o CPF: " + cpf);
            }
            if(!pessoa.getAtivo()){
                return ResponseEntity.badRequest().body("Pessoa física não está ativa");
            }

            pessoa.setAtivo(false);
            pesfServ.updateFisicalPerson(pessoa);
            Action action = new Action();
            action.setDtacao(dtacao);
            action.setDescricao(descricao);
            action.setUsuario(null);
            action.setPessoa(pessoa);


            actServ.addAction(action);

            return ResponseEntity.ok("Ação inserida com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao inserir ação: " + e.getMessage());
        }
    }


    @GetMapping("/delete-action")
    public ResponseEntity<Object> deleteAction (@RequestParam(value="aco_id") Long aco_id) {
        if(actServ.deleteById(aco_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-all-Actions")
    public ResponseEntity<Object> getAllActions() {
        return new ResponseEntity<>(actServ.getAll(),HttpStatus.OK);
    }

}
