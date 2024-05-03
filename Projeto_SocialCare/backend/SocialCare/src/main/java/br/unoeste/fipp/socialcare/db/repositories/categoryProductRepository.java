package br.unoeste.fipp.socialcare.db.repositories;

import br.unoeste.fipp.socialcare.db.entities.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryProductRepository extends JpaRepository<CategoryProduct, Long> {
}
