package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.Product;
import br.unoeste.fipp.socialcare.DataBase.entities.Storage;
import br.unoeste.fipp.socialcare.DataBase.entities.Unity;
import br.unoeste.fipp.socialcare.DataBase.repositories.storageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class storageService {
    @Autowired
    private storageRepository storageRepo;

    public Storage addStorage (Storage storage) {
        return storageRepo.save(storage);
    }

    public boolean deleteById (Long id) {
        try{
            storageRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public Storage getIdByNameB(String storage)
    {
        Storage novo = storageRepo.findByProductNome(storage);

        return novo;

    }
    public Storage getByName(String storage)
    {
        Storage novo = storageRepo.findByProductNome(storage);

        return novo;

    }
    public Storage getById (Long id) {
        Storage storage = storageRepo.findById(id).get();
        return storage;
    }

    public List<Storage> getAll() {
        return storageRepo.findAll();
    }


    public List<Product> getProdutosNoEstoque() {

            List<Product> produtos = new ArrayList<>();
            List<Storage> registros = storageRepo.findAll(); // Obtém todos os registros de estoque

            // Itera sobre os registros de estoque e adiciona os produtos à lista
            for (Storage registro : registros) {
                produtos.add(registro.getProduct());
            }

           return produtos;

    }
    public List<Unity> getUnidadesNoEstoque() {

        List<Unity> produtos = new ArrayList<>();
        List<Storage> registros = storageRepo.findAll(); // Obtém todos os registros de estoque

        // Itera sobre os registros de estoque e adiciona os produtos à lista
        for (Storage unidade : registros) {
            produtos.add(unidade.getUnity());
        }

        return produtos;

    }
}