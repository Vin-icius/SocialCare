package br.unoeste.fipp.socialcare.RestControls.useCases;

import br.unoeste.fipp.socialcare.DataBase.entities.City;
import br.unoeste.fipp.socialcare.DataBase.entities.FisicalPerson;
import br.unoeste.fipp.socialcare.DataBase.entities.Gender;
import br.unoeste.fipp.socialcare.Services.cityService;
import br.unoeste.fipp.socialcare.Services.fisicalPersonService;
import br.unoeste.fipp.socialcare.Services.genderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("apis/FisicalPerson")
public class fisicalPersonControl {
    @Autowired
    fisicalPersonService fispService;
    @Autowired
    genderService genService;
    @Autowired
    cityService cService;

    @PostMapping("/add-fisical-person")
    public ResponseEntity<Object> addPerson(@RequestBody FisicalPerson pessoa) {
        try {
            if (pessoa.getCidade() == null || pessoa.getCidade().getId() == null) {
                return ResponseEntity.badRequest().body("ID da cidade não pode ser nulo.");
            }
            if (pessoa.getGenero() == null || pessoa.getGenero().getId() == null) {
                return ResponseEntity.badRequest().body("ID do gênero não pode ser nulo.");
            }

            // Verificar se o CPF já está cadastrado
            if (fispService.existsByCpf(pessoa.getCpf())) {
                return ResponseEntity.badRequest().body("CPF já cadastrado.");
            }

            City cidade = cService.getById(pessoa.getCidade().getId());
            Gender genero = genService.getById(pessoa.getGenero().getId());

            if (cidade == null) {
                return ResponseEntity.badRequest().body("Cidade não encontrada.");
            }
            if (genero == null) {
                return ResponseEntity.badRequest().body("Gênero não encontrado.");
            }

            pessoa.setCidade(cidade);
            pessoa.setGenero(genero);

            fispService.addFisicalPerson(pessoa);

            return ResponseEntity.ok("Pessoa inserida com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao inserir pessoa física: " + e.getMessage());
        }
    }

    @GetMapping("/get-fisical-person")
    public ResponseEntity<Object> getPersonFisical (@RequestParam(value="pesf_cpf") String pesf_cpf) {
        return new ResponseEntity<>(fispService.getByCpf(pesf_cpf), HttpStatus.OK);
    }

    @GetMapping("/get-all-fisical-persons")
    public ResponseEntity<Object> getAllPersonsFisical() {
        return new ResponseEntity<>(fispService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/delete-fisical-person")
    public ResponseEntity<Object> deletePersonFisical(@RequestParam("pesf_cpf")String pesf_cpf)
    {
        if(fispService.deleteByCpf(pesf_cpf))
            return ResponseEntity.ok("Pessoa Fisica excluida com sucesso!");
        else
            return ResponseEntity.badRequest().body("Erro ao excluir Pessoa Fisica!");
    }

}
