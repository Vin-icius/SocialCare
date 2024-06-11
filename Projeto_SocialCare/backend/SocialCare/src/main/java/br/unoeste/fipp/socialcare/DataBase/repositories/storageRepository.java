package br.unoeste.fipp.socialcare.DataBase.repositories;

import br.unoeste.fipp.socialcare.DataBase.entities.Storage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface storageRepository extends JpaRepository<Storage, Long> {

    @Modifying
    @Transactional
    @Query(value="INSERT into estoque (unidade_uni_id, produto_pro_id, est_quantidade) VALUES (:unidade_uni_id,:produto_pro_id,:est_quantidade)",
            nativeQuery = true)
    public void insertInStorage(@Param("unidade_uni_id") Long unidade_uni_id,@Param("produto_pro_id") Long produto_pro_id,
                                @Param("est_quantidade") int est_quantidade);

}
