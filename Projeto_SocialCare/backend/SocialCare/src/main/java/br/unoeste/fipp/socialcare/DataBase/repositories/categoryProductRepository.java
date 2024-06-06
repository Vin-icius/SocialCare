package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryProductRepository extends JpaRepository<CategoryProduct, Long> {
}
