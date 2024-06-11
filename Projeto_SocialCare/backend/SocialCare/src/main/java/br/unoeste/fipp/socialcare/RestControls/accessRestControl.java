package br.unoeste.fipp.socialcare.RestControls;

import br.unoeste.fipp.socialcare.DataBase.entities.User;
//import br.unoeste.fipp.socialcare.Security.JWTTokenProvider;
import br.unoeste.fipp.socialcare.Services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="apis/security/")
public class accessRestControl {
        @Autowired
        private userService userService;

        @PostMapping(value="/login")
        public ResponseEntity<Object> login(@RequestBody User user){
                try {
                        User existingUser = userService.getByEmail(user.getEmail());
                        if(existingUser == null) {
                                return ResponseEntity.badRequest().body("Usuário não cadastrado");
                        }

                        if(!existingUser.isActive()) {
                                return ResponseEntity.badRequest().body("Usuário inativo");
                        }

                        if(existingUser.getPassword().equals(user.getPassword())) {
                                // Gera o token JWT
//                                String token = JWTTokenProvider.getToken(existingUser.getEmail(), String.valueOf(existingUser.getLevel()));
//                                return ResponseEntity.ok(token);
                                return null;
                        } else {
                                return ResponseEntity.badRequest().body("Senha incorreta");
                        }
                } catch (Exception e) {
                        return ResponseEntity.badRequest().body("Erro ao autenticar usuário: " + e.getMessage());
                }
        }

        @GetMapping("/get-user")
        public ResponseEntity<Object> getUser(@RequestParam(value="usu_id") Long usu_id)
        {
                return ResponseEntity.ok(userService.getById(usu_id));
        }
}