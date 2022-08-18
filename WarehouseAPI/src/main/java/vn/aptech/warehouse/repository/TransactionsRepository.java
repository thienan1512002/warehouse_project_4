package vn.aptech.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.warehouse.entity.Supplier;
import vn.aptech.warehouse.entity.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Object> {
    @Query("SELECT b FROM Transactions b WHERE b.id=:id")
    Transactions findById(@Param("id")int id);
}
