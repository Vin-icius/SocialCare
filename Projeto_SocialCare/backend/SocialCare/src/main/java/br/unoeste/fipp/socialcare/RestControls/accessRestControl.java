package br.unoeste.fipp.socialcare.RestControls;

import br.unoeste.fipp.socialcare.DataBase.entities.User;
import br.unoeste.fipp.socialcare.Security.JWTTokenProvider;
import br.unoeste.fipp.socialcare.Services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="apis/security/")
public class accessRestControl {
        @Autowired
        private userService usService;

        @GetMapping(value="connection-test")
        public String connectionTest(){
            return "connected";
        }

        @PostMapping(value="/login")
        public ResponseEntity<Object> login(@RequestBody User user){
                try {
                        String token="not authentic";
                        //acesso ao banco de dados para verificar a existencia do usuario
                        //comparar a senha
                        if(user.getPassword()==""+123){
                                token = JWTTokenProvider.getToken(user.getEmail(),""+user.getLevel());
                                return ResponseEntity.ok(token);
                        }
                        return ResponseEntity.badRequest().body(token);

                }catch (Exception e){
                        return ResponseEntity.badRequest().body("Erro ao cadastrar usuario "+e.getMessage());
                }
        }
        @GetMapping("/get-user")
        public ResponseEntity<Object> getUser(@RequestParam(value="usu_id") Long usu_id)
        {
            return new ResponseEntity<>(usService.getById(usu_id),HttpStatus.OK);
        }
}