package br.unoeste.fipp.socialcare.RestControls.useCases;

import br.unoeste.fipp.socialcare.DataBase.entities.*;
import br.unoeste.fipp.socialcare.DataBase.repositories.actionRepository;
import br.unoeste.fipp.socialcare.Services.actionService;
import br.unoeste.fipp.socialcare.Services.fisicalPersonService;
import br.unoeste.fipp.socialcare.Services.planService;
import br.unoeste.fipp.socialcare.Services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apis/CloseReception")
public class closeReceptionControl {



    @Autowired
    private actionService actServ;

    @Autowired
    private actionRepository acRepo;
    @Autowired
    private fisicalPersonService pesfServ;
    @Autowired userService usServ;


    @PostMapping("/add-action")
    public ResponseEntity<Object> addAction(@RequestParam("cpf") String cpf,
                                            @RequestParam("dtacao") String dtacao,
                                            @RequestParam("descricao") String descricao,
                                            @RequestParam("motivo") String motivo,
                                            @RequestParam("idade") String idade) {
        try {

            // Buscar a pessoa física pelo CPF
            FisicalPerson pessoa = pesfServ.getByCpf(cpf);
            if (pessoa == null) {
                return ResponseEntity.badRequest().body("Pessoa física não encontrada para o CPF: " + cpf);
            }
            if(!pessoa.getAtivo()){
                return ResponseEntity.badRequest().body("Pessoa física não está ativa");
            }
            /*
            User user=usServ.getByEmail(email);
            if(user == null)
            {
                return ResponseEntity.badRequest().body("Email não cadastrado");
            }
            if(!user.getPassword().equals(senha))
            {
                return ResponseEntity.badRequest().body("Senha invalida");
            }
            */
            int idadeInt = Integer.parseInt(idade);
            if(idadeInt <= 0)
            {
                return ResponseEntity.badRequest().body("Idade invalida");
            }



            pessoa.setAtivo(false);
            pesfServ.updateFisicalPerson(pessoa);
            Action action = new Action();
            action.setDtacao(dtacao);
            action.setDescricao(descricao);
            action.setMotivo(motivo);
            action.setIdade(idade);
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
    public ResponseEntity<Object> deleteAction(@RequestParam(value = "cpf") String cpf) {
        try {
            // Busca a pessoa física pelo CPF
            FisicalPerson pessoa = pesfServ.getByCpf(cpf);
            if (pessoa == null) {
                return ResponseEntity.badRequest().body("Pessoa física não encontrada para o CPF: " + cpf);
            }

            // Verifica se a pessoa está ativa
            if (pessoa.getAtivo()) {
                return ResponseEntity.badRequest().body("Pessoa está ativa. Não é possível excluir a ação.");
            }

            // Exclui a ação associada à pessoa
            Action action = acRepo.findByPessoa(pessoa);
            if (action == null) {
                return ResponseEntity.badRequest().body("Ação não encontrada para a pessoa com CPF: " + cpf);
            }
            actServ.deleteById(action.getId());

            // Atualiza o estado da pessoa para ativo=true
            pessoa.setAtivo(true);
            pesfServ.updateFisicalPerson(pessoa);

            return ResponseEntity.ok("Ação excluída com sucesso para a pessoa com CPF: " + cpf);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao excluir ação: " + e.getMessage());
        }
    }


    @GetMapping("/get-all-actions")
    public ResponseEntity<Object> getAllActions() {
        return new ResponseEntity<>(actServ.getAll(),HttpStatus.OK);
    }

}
