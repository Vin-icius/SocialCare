package br.unoeste.fipp.socialcare.Services;


import br.unoeste.fipp.socialcare.DataBase.entities.Compra;
import br.unoeste.fipp.socialcare.DataBase.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class compraService {
    @Autowired
    private CompraRepository compraRepo;
    public Compra addCompra (Compra Compra) {
        return compraRepo.save(Compra);
    }

    public boolean deleteById (Long id) {
        try{
            compraRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public Compra getIdByName(Compra compra)
    {
       Compra novo = compraRepo.findByProductName(compra.getDesc());

       return novo;

    }
    public Compra getById (Long id) {
        Compra Compra = compraRepo.findById(id).get();
        return Compra;
    }

    public List<Compra> getAll() {
        return compraRepo.findAll();
    }
    public boolean findByIdB(Long id)
    {
        try{
            compraRepo.findById(id);

        }catch (Exception e){
            return false;
        }
        return true;
    }
}
