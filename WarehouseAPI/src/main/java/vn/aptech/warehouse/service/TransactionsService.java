package vn.aptech.warehouse.service;


import vn.aptech.warehouse.entity.Transactions;
import java.util.List;

public interface TransactionsService {
    List<Transactions> findAll();

    Transactions findById(int id);

    Transactions save(Transactions trans);
}
