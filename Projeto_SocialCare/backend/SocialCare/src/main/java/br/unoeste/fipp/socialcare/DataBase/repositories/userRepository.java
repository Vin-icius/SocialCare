package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface userRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u WHERE u.level = 1 AND u.active = true")
    long countByFullAccessAndActive();

    User findByEmail(String email); // Adicionando o método para buscar usuário por email
}