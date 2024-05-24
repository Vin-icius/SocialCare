package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface productRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Transactional
    @Query(value="INSERT into estoque (fee_texto, den_id) VALUES (:texto,:den_id)",
            nativeQuery = true)
    public void insertStorage(@Param("den_id") Long den_id, @Param("texto") String texto);
}