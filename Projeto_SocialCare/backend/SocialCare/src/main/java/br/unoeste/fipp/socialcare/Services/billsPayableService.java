package br.unoeste.fipp.socialcare.Services;

import br.unoeste.fipp.socialcare.DataBase.entities.BillsPayable;
import br.unoeste.fipp.socialcare.DataBase.repositories.billsPayableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class billsPayableService {
    @Autowired
    private billsPayableRepository billsPayableRepo;

    public BillsPayable addBillsPayable (BillsPayable billsPayable) {
        return billsPayableRepo.save(billsPayable);
    }

    public boolean deleteById(Long id) {
        try {
            billsPayableRepo.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public BillsPayable getById(Long id) {
        return billsPayableRepo.findById(id).orElse(null);
    }

    public List<BillsPayable> getAll() {
        return billsPayableRepo.findAll();
    }

    public BillsPayable updateBillsPayable (BillsPayable billsPayable) {
        if (billsPayableRepo.existsById(billsPayable.getId())) {
            return billsPayableRepo.save(billsPayable);
        } else {
            throw new IllegalArgumentException("Contas a Pagar não encontrado");
        }
    }
}