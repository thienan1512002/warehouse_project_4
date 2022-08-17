package vn.aptech.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.warehouse.entity.Supplier;
import vn.aptech.warehouse.entity.Transactions;
import vn.aptech.warehouse.repository.SupplierRepository;
import vn.aptech.warehouse.repository.TransactionsRepository;
import vn.aptech.warehouse.service.TransactionsService;

import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService {
    @Autowired
    private TransactionsRepository repo;


    @Override
    public List<Transactions> findAll() {
        return repo.findAll();
    }

    @Override
    public Transactions findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Transactions save(Transactions trans) {
        return repo.save(trans);
    }
}
