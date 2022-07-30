/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.warehouse.entity.SaleOrder;

/**
 *
 * @author nhta1
 */
public interface SaleOrderRepo extends JpaRepository<SaleOrder, String>{
    
    @Query("SELECT o FROM SaleOrder o WHERE o.complete = false")
    public List<SaleOrder> findByCompleted();
    @Query("SELECT o FROM SaleOrder o WHERE o.complete = false and o.so_id=:id")
    public SaleOrder findBySoId(@Param("id") String so_id);
}
