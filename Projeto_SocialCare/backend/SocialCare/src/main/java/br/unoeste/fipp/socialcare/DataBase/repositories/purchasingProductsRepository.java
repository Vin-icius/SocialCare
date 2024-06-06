package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.PurchasingProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface purchasingProductsRepository extends JpaRepository<PurchasingProducts,Long> {
    public boolean findByIdB(Long id);

}
