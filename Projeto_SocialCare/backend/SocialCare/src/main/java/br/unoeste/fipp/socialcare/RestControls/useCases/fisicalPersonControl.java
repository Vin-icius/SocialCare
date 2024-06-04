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
    public ResponseEntity<String> deletePersonFisical(@RequestParam("pesf_cpf") String cpf) {
        Long id = fispService.getIdByCpf(cpf);
        if (id != null && fispService.deleteById(id)) {
            return ResponseEntity.ok("Pessoa Fisica excluida com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Erro ao excluir Pessoa Fisica!");
        }
    }

    @PostMapping("/update-fisical-person")
    public ResponseEntity<Object> updatePerson(@RequestBody FisicalPerson pessoa) {
        try {
            // Verificar se o CPF está presente
            if (pessoa.getCpf() == null || pessoa.getCpf().isEmpty()) {
                return ResponseEntity.badRequest().body("CPF não pode ser nulo ou vazio.");
            }

            // Buscar a pessoa física pelo CPF
            FisicalPerson existingPerson = fispService.getByCpf(pessoa.getCpf());
            if (existingPerson == null) {
                return ResponseEntity.badRequest().body("Pessoa física não encontrada com o CPF fornecido.");
            }

            // Atualizar os dados da pessoa física, exceto o CPF
            existingPerson.setNome(pessoa.getNome());
            existingPerson.setLogradouro(pessoa.getLogradouro());
            existingPerson.setBairro(pessoa.getBairro());
            existingPerson.setCep(pessoa.getCep());
            existingPerson.setEmail(pessoa.getEmail());
            existingPerson.setRg(pessoa.getRg());
            existingPerson.setDtnascimento(pessoa.getDtnascimento());
            existingPerson.setCidade(pessoa.getCidade());
            existingPerson.setGenero(pessoa.getGenero());

            // Salvar a pessoa física atualizada
            fispService.updateFisicalPerson(existingPerson);

            return ResponseEntity.ok("Pessoa física atualizada com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao atualizar pessoa física: " + e.getMessage());
        }
    }

}
